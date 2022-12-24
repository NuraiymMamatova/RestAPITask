package com.peaksoft.project_on_restapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String email;

    private String firstName;

    private String password;

    public UserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserRequest(String email, String firstName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.password = password;
    }
}
