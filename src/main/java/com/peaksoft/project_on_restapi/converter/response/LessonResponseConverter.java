package com.peaksoft.project_on_restapi.converter.response;

import com.peaksoft.project_on_restapi.dto.response.LessonResponse;
import com.peaksoft.project_on_restapi.model.entity.Lesson;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class LessonResponseConverter {

    private List<LessonResponse> lessonResponseList;

    public LessonResponse viewLesson(Lesson lesson) {
        if (lesson == null) {
            return null;
        }
        LessonResponse lessonResponse = new LessonResponse();
        lessonResponse.setId(lesson.getId());
        lessonResponse.setLessonName(lesson.getLessonName());
        return lessonResponse;
    }

    public List<LessonResponse> viewAllLesson(List<Lesson> lessons) {
        List<LessonResponse> lessonResponses = new ArrayList<>();
        for (Lesson lesson : lessons) {
            lessonResponses.add(viewLesson(lesson));
        }
        return lessonResponses;
    }
}
