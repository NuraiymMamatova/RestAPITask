package com.peaksoft.project_on_restapi.api;

import com.peaksoft.project_on_restapi.dto.request.LessonRequest;
import com.peaksoft.project_on_restapi.dto.response.LessonResponse;
import com.peaksoft.project_on_restapi.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson_api")
public class LessonApi {

    private final LessonService lessonService;

    @PostMapping("/save")
    public LessonResponse saveLesson(@RequestBody LessonRequest lessonRequest) {
        return lessonService.saveLesson(lessonRequest);
    }

    @GetMapping("/all")
    public List<LessonResponse> findAllLessons() {
        return lessonService.viewAllLessons();
    }

    @GetMapping("/{lessonId}")
    public LessonResponse findById(@PathVariable Long lessonId) {
        return lessonService.findLessonById(lessonId);
    }

    @DeleteMapping("/{lessonId}")
    public LessonResponse deleteLessonById(@PathVariable Long lessonId) {
        return lessonService.deleteLessonById(lessonId);
    }

    @PutMapping("/{lessonId}")
    public LessonResponse updateLesson(@PathVariable Long lessonId,
                                       @RequestBody LessonRequest lessonRequest) {
        return lessonService.updateLesson(lessonId, lessonRequest);
    }
}