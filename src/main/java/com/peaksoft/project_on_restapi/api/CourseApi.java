package com.peaksoft.project_on_restapi.api;

import com.peaksoft.project_on_restapi.dto.request.CourseRequest;
import com.peaksoft.project_on_restapi.dto.response.CourseResponse;
import com.peaksoft.project_on_restapi.service.CourseService;
import com.peaksoft.project_on_restapi.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course_api")
public class CourseApi {

    private final CourseService courseService;

    private final GroupService groupService;

    @PostMapping("/save")
    public CourseResponse saveCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.saveCourse(courseRequest);
    }

    @PostMapping("/save/{companyId}")
    public CourseResponse saveCourse(@PathVariable Long companyId, @RequestBody CourseRequest courseRequest) {
        return courseService.saveCourse(companyId, courseRequest);
    }

    @GetMapping("/all")
    public List<CourseResponse> findALlCourses() {
        return courseService.viewAllCourses();
    }

    @GetMapping("/{courseId}")
    public CourseResponse findById(@PathVariable Long courseId) {
        return courseService.findCourseById(courseId);
    }

    @DeleteMapping("/{courseId}")
    public CourseResponse deleteCourseById(@PathVariable Long courseId) {
        return courseService.deleteCourseById(courseId);
    }

    @PutMapping("/{courseId}")
    public CourseResponse updateCourse(@PathVariable Long courseId, @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(courseId, courseRequest);
    }

    @PostMapping("/{courseId}/assignGroupToCourse/{groupId}")
    private CourseResponse assignGroupToCourse(@PathVariable Long courseId, @PathVariable Long groupId) {
        groupService.assignGroupToCourse(groupId, courseId);
        return courseService.findCourseById(courseId);
    }

}
