package com.peaksoft.project_on_restapi.dto.request;

import com.peaksoft.project_on_restapi.model.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CompanyRequest {

    private String companyName;

    private String locatedCountry;

    private List<Course> courses;

}
