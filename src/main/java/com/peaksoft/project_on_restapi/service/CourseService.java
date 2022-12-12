package com.peaksoft.project_on_restapi.service;

import com.peaksoft.project_on_restapi.dto.request.CourseRequest;
import com.peaksoft.project_on_restapi.dto.response.CourseResponse;
import com.peaksoft.project_on_restapi.model.entity.Course;

import java.util.List;

public interface CourseService {

    CourseResponse saveCourse(Long companyId, CourseRequest courseRequest);

    CourseResponse deleteCourseById(Long courseId);

    CourseResponse updateCourse(Long courseId, CourseRequest courseRequest);

    CourseResponse findCourseById(Long courseId);

    List<CourseResponse> viewAllCourses();
}
