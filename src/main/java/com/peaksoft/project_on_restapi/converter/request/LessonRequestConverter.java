package com.peaksoft.project_on_restapi.converter.request;

import com.peaksoft.project_on_restapi.dto.request.LessonRequest;
import com.peaksoft.project_on_restapi.model.entity.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonRequestConverter {

    public Lesson saveLesson(LessonRequest lessonRequest) {
        if (lessonRequest == null) {
            return null;
        }
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.getLessonName());
        return lesson;
    }

    public void update(Lesson lesson, LessonRequest lessonRequest) {
        lesson.setLessonName(lessonRequest.getLessonName());
    }
}
