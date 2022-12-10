package com.peaksoft.project_on_restapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InstructorRequest {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String specialization;
}
