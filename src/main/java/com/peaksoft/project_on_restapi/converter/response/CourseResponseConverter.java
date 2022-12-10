package com.peaksoft.project_on_restapi.converter.response;

import com.peaksoft.project_on_restapi.dto.response.CourseResponse;
import com.peaksoft.project_on_restapi.model.entity.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseResponseConverter {

    public CourseResponse viewCourse(Course course) {
        if (course == null) {
            return null;
        }
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setDuration(course.getDuration());
        courseResponse.setDescription(course.getDescription());
        return courseResponse;
    }

    public List<CourseResponse> viewAllCourse(List<Course> courses) {
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course : courses) {
            courseResponses.add(viewCourse(course));
        }
        return courseResponses;
    }
}
