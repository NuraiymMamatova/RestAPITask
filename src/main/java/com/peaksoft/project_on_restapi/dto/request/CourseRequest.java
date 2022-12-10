package com.peaksoft.project_on_restapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourseRequest {

    private String courseName;

    private int duration;

    private String description;

}
