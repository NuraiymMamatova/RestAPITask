package com.peaksoft.project_on_restapi.api;

import com.peaksoft.project_on_restapi.converter.response.LessonResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.LessonRequest;
import com.peaksoft.project_on_restapi.dto.response.LessonResponse;
import com.peaksoft.project_on_restapi.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson_api")
public class LessonApi {

    private final LessonService lessonService;

    @PostMapping("/save/{courseId}")
    @PreAuthorize("hasAnyRole('ROLE_INSTRUCTOR', 'ROLE_ADMIN')")
    public LessonResponse saveLesson(@PathVariable Long courseId, @RequestBody LessonRequest lessonRequest) {
        return lessonService.saveLesson(courseId, lessonRequest);
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public LessonResponseConverter findAllLessons(@RequestParam(name = "name", required = false) String name,
                                                  @RequestParam int page,
                                                  @RequestParam int size) {
        return lessonService.getAll(name, page, size);
    }

    @GetMapping("/all/{courseId}")
    @PreAuthorize("isAuthenticated()")
    public List<LessonResponse> findAllLessons(@PathVariable Long courseId) {
        return lessonService.viewAllLessons(courseId);
    }

    @GetMapping("/{lessonId}")
    @PreAuthorize("isAuthenticated()")
    public LessonResponse findById(@PathVariable Long lessonId) {
        return lessonService.findLessonById(lessonId);
    }

    @DeleteMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public LessonResponse deleteLessonById(@PathVariable Long lessonId) {
        return lessonService.deleteLessonById(lessonId);
    }

    @PutMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public LessonResponse updateLesson(@PathVariable Long lessonId,
                                       @RequestBody LessonRequest lessonRequest) {
        return lessonService.updateLesson(lessonId, lessonRequest);
    }
}
