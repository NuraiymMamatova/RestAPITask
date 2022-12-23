package com.peaksoft.project_on_restapi.service.impl;

import com.peaksoft.project_on_restapi.converter.request.CourseRequestConverter;
import com.peaksoft.project_on_restapi.converter.response.CourseResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.CourseRequest;
import com.peaksoft.project_on_restapi.dto.response.CourseResponse;
import com.peaksoft.project_on_restapi.dto.response.UserResponse;
import com.peaksoft.project_on_restapi.model.entity.*;
import com.peaksoft.project_on_restapi.repository.CompanyRepository;
import com.peaksoft.project_on_restapi.repository.CourseRepository;
import com.peaksoft.project_on_restapi.service.CourseService;
import com.peaksoft.project_on_restapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CompanyRepository companyRepository;

    private final UserService userService;

    private final CourseRequestConverter courseRequestConverter;

    private final CourseResponseConverter courseResponseConverter;

    @Override
    public CourseResponse saveCourse(Long companyId, CourseRequest courseRequest) {
        Course course = courseRequestConverter.saveCourse(courseRequest);
        Company company = companyRepository.findById(companyId).get();
        course.setCompany(company);
        company.addCourse(course);
        courseRepository.save(course);
        return courseResponseConverter.viewCourse(course);
    }

    @Override
    public CourseResponseConverter getAll(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        courseResponseConverter.setCourseResponseList(viewPagination(search(name, pageable)));
        return courseResponseConverter;
    }

    @Override
    public List<CourseResponse> viewPagination(List<Course> courses) {
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for (Course course : courses) {
            courseResponseList.add(courseResponseConverter.viewCourse(course));
        }
        return courseResponseList;
    }

    @Override
    public List<Course> search(String name, Pageable pageable) {
        String courseName = name == null ? "" : name;
        return courseRepository.searchPagination(courseName.toUpperCase(), pageable);
    }

    @Override
    public CourseResponse deleteCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        //
        Long count = 0L;
        for (Group group : course.getGroups()) {
            for (Student student : group.getStudents()) {
                count++;
                UserResponse user = userService.findUserByEmail(student.getEmail());
                userService.deleteUserById(Long.valueOf(user.getId()));
            }
        }
        Long count1 = course.getCompany().getCount();
        count1 -= count;
        course.getCompany().setCount(count1);
        for (Instructor instructor : course.getInstructors()) {
            UserResponse user = userService.findUserByEmail(instructor.getEmail());
            userService.deleteUserById(Long.valueOf(user.getId()));
        }
        //
        courseRepository.delete(course);
        return courseResponseConverter.viewCourse(course);
    }

    @Override
    public CourseResponse updateCourse(Long courseId, CourseRequest courseRequest) {
        Course course = courseRepository.findById(courseId).get();
        courseRequestConverter.update(course, courseRequest);
        return courseResponseConverter.viewCourse(courseRepository.save(course));
    }

    @Override
    public CourseResponse findCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        return courseResponseConverter.viewCourse(course);
    }

    @Override
    public List<CourseResponse> viewAllCourses() {
        return courseResponseConverter.viewAllCourse(courseRepository.findAll());
    }

    @Override
    public List<CourseResponse> viewAllCourses(Long companyId) {
        return courseResponseConverter.viewAllCourse(courseRepository.getAllCoursesByCompanyId(companyId));
    }
}
