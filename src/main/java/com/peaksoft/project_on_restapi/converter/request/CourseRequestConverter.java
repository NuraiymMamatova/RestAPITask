package com.peaksoft.project_on_restapi.converter.request;

import com.peaksoft.project_on_restapi.dto.request.CourseRequest;
import com.peaksoft.project_on_restapi.model.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseRequestConverter {

    public Course saveCourse(CourseRequest courseRequest) {
        if (courseRequest == null) {
            return null;
        }
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());
        course.setDescription(courseRequest.getDescription());
        return course;
    }

    public void update(Course course, CourseRequest courseRequest) {
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());
        course.setDescription(courseRequest.getDescription());

    }
}
