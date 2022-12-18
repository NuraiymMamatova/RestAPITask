package com.peaksoft.project_on_restapi.dto.request;

import com.peaksoft.project_on_restapi.model.enums.StudyFormat;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
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

    private String password;

    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
}
