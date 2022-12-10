package com.peaksoft.project_on_restapi.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonRequest {
    private String lessonName;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public LessonRequest(String lessonName) {
        this.lessonName = lessonName;
    }
}
