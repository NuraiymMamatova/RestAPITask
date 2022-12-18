package com.peaksoft.project_on_restapi.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class LoginResponse {

    private String jwtToken;

    private String deadline;//

    private String message;

    private Set<String> authorities;

}