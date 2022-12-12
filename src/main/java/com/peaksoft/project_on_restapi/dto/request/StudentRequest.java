package com.peaksoft.project_on_restapi.dto.request;

import com.peaksoft.project_on_restapi.model.enums.StudyFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentRequest {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
}
