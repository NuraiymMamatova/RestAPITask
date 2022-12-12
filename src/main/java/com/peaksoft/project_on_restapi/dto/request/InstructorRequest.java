package com.peaksoft.project_on_restapi.dto.request;

import jakarta.validation.constraints.Email;
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

    @Email
    private String email;

    private String specialization;
}
