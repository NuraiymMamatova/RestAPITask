package com.peaksoft.project_on_restapi.service.impl;

import com.peaksoft.project_on_restapi.converter.request.InstructorRequestConverter;
import com.peaksoft.project_on_restapi.converter.response.InstructorResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.InstructorRequest;
import com.peaksoft.project_on_restapi.dto.response.InstructorResponse;
import com.peaksoft.project_on_restapi.model.entity.Course;
import com.peaksoft.project_on_restapi.model.entity.Group;
import com.peaksoft.project_on_restapi.model.entity.Instructor;
import com.peaksoft.project_on_restapi.model.entity.Student;
import com.peaksoft.project_on_restapi.repository.CourseRepository;
import com.peaksoft.project_on_restapi.repository.InstructorRepository;
import com.peaksoft.project_on_restapi.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    private final CourseRepository courseRepository;

    private final InstructorRequestConverter instructorRequestConverter;

    private final InstructorResponseConverter instructorResponseConverter;

    @Override
    public InstructorResponse saveInstructor(Long courseId, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRequestConverter.saveInstructor(instructorRequest);
        Course course = courseRepository.findById(courseId).get();
        //
        Long count = 0L;
        for (Group group : course.getGroups()) {
            for (Student student : group.getStudents()) {
                  count++;

            }
        }
        instructor.setCount(count);
        //
        course.addInstructor(instructor);
        instructor.setCourse(course);
        instructorRepository.save(instructor);
        return instructorResponseConverter.viewInstructor(instructor);
    }

    @Override
    public InstructorResponse deleteInstructorById(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        instructorRepository.delete(instructor);
        return instructorResponseConverter.viewInstructor(instructor);
    }

    @Override
    public InstructorResponse updateInstructor(Long instructorId, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        instructorRequestConverter.update(instructor, instructorRequest);
        return instructorResponseConverter.viewInstructor(instructorRepository.save(instructor));
    }

    @Override
    public InstructorResponse findInstructorById(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        return instructorResponseConverter.viewInstructor(instructor);
    }

    @Override
    public List<InstructorResponse> viewAllInstructors() {
        return instructorResponseConverter.viewAllInstructor(instructorRepository.findAll());
    }

    @Override
    public List<InstructorResponse> viewAllInstructors(Long courseId) {
        return instructorResponseConverter.viewAllInstructor(instructorRepository.getAllInstructorByCourseId(courseId));
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) {
        if (instructorId != null) {
            Instructor instructor = instructorRepository.findById(instructorId).get();
            if (courseId != null) {
                Course course = courseRepository.findById(courseId).get();
                //
                Long count = 0L;
                for (Group group : course.getGroups()) {
                    for (Student student : group.getStudents()) {
                        count ++;
                    }
                }
                instructor.setCount(count);
                //
                instructor.setCourse(course);
                course.addInstructor(instructor);
                instructorRepository.save(instructor);
            }else {
                System.out.println("course is null");
            }
        }else {
            System.out.println("instructor is null");
        }
    }
}
