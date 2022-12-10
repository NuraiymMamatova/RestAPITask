package com.peaksoft.project_on_restapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {

    private Long id;

    private String courseName;

    private int duration;

    private String description;

}
