package com.peaksoft.project_on_restapi.service;

import com.peaksoft.project_on_restapi.converter.response.LessonResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.LessonRequest;
import com.peaksoft.project_on_restapi.dto.response.LessonResponse;
import com.peaksoft.project_on_restapi.model.entity.Lesson;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LessonService {

   LessonResponse saveLesson(Long  courseId, LessonRequest lessonRequest);

   LessonResponse deleteLessonById(Long lessonId);

   LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest);

   LessonResponse findLessonById(Long lessonId);

   List<LessonResponse> viewAllLessons();

    List<LessonResponse> viewAllLessons(Long courseId);

    LessonResponseConverter getAll(String name, int page, int size);

    List<LessonResponse> viewPagination(List<Lesson> lessons);

    List<Lesson> search(String name, Pageable pageable);
}
