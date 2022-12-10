package com.peaksoft.project_on_restapi.service.impl;

import com.peaksoft.project_on_restapi.converter.request.CourseRequestConverter;
import com.peaksoft.project_on_restapi.converter.response.CourseResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.CourseRequest;
import com.peaksoft.project_on_restapi.dto.response.CourseResponse;
import com.peaksoft.project_on_restapi.model.entity.Course;
import com.peaksoft.project_on_restapi.repository.CourseRepository;
import com.peaksoft.project_on_restapi.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CourseRequestConverter courseRequestConverter;

    private final CourseResponseConverter courseResponseConverter;

    @Override
    public CourseResponse saveCourse(CourseRequest courseRequest) {
//        Course course = courseRequestConverter.saveCourse(courseRequest);
//        courseRepository.save(course);
//        return courseResponseConverter.viewCourse(course);
        return null;
    }

    @Override
    public CourseResponse deleteCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
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
}
