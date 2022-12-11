package com.peaksoft.project_on_restapi.dto.response;

import com.peaksoft.project_on_restapi.model.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {

    private Long id;

    private String companyName;

    private String locatedCountry;

    //
    private Long count;
    //

    private List<Course> courses;

}
