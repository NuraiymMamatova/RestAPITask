package com.peaksoft.project_on_restapi.service.impl;

import com.peaksoft.project_on_restapi.converter.request.LessonRequestConverter;
import com.peaksoft.project_on_restapi.converter.response.LessonResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.LessonRequest;
import com.peaksoft.project_on_restapi.dto.response.LessonResponse;
import com.peaksoft.project_on_restapi.model.entity.Course;
import com.peaksoft.project_on_restapi.model.entity.Lesson;
import com.peaksoft.project_on_restapi.repository.CourseRepository;
import com.peaksoft.project_on_restapi.repository.LessonRepository;
import com.peaksoft.project_on_restapi.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    private final CourseRepository courseRepository;

    private final LessonRequestConverter lessonRequestConverter;

    private final LessonResponseConverter lessonResponseConverter;

    @Override
    public LessonResponse saveLesson(Long courseId, LessonRequest lessonRequest) {
        Lesson lesson = lessonRequestConverter.saveLesson(lessonRequest);
        Course course = courseRepository.findById(courseId).get();
        course.addLesson(lesson);
        lesson.setCourse(course);
        lessonRepository.save(lesson);
        return lessonResponseConverter.viewLesson(lesson);
    }

    @Override
    public LessonResponse deleteLessonById(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        lessonRepository.delete(lesson);
        return lessonResponseConverter.viewLesson(lesson);
    }

    @Override
    public LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        lessonRequestConverter.update(lesson, lessonRequest);
        return lessonResponseConverter.viewLesson(lessonRepository.save(lesson));
    }

    @Override
    public LessonResponse findLessonById(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        return lessonResponseConverter.viewLesson(lesson);
    }

    @Override
    public List<LessonResponse> viewAllLessons() {
        return lessonResponseConverter.viewAllLesson(lessonRepository.findAll());
    }

    @Override
    public List<Lesson> getAllLessons(Long id) {
        return null;
    }
}
