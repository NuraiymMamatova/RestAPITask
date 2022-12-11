package com.peaksoft.project_on_restapi.service;

import com.peaksoft.project_on_restapi.dto.request.LessonRequest;
import com.peaksoft.project_on_restapi.dto.response.LessonResponse;
import com.peaksoft.project_on_restapi.model.entity.Lesson;

import java.util.List;

public interface LessonService {

   LessonResponse saveLesson(LessonRequest lessonRequest);

   LessonResponse saveLesson(Long  courseId, LessonRequest lessonRequest);

   LessonResponse deleteLessonById(Long lessonId);

   LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest);

   LessonResponse findLessonById(Long lessonId);

   List<LessonResponse> viewAllLessons();

    List<Lesson> getAllLessons(Long id);
}
