package com.peaksoft.project_on_restapi.converter.request;

import com.peaksoft.project_on_restapi.dto.response.LoginResponse;
import com.peaksoft.project_on_restapi.model.entity.Role;
import com.peaksoft.project_on_restapi.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class LoginConverter {

    public LoginResponse loginView(String token,
                                   String deadline,
                                   String message,
                                   User user) {
        var loginResponse = new LoginResponse();
        if (user != null) {
            setAuthorite(loginResponse, user.getRoles());
        }
        loginResponse.setDeadline(deadline);
        loginResponse.setJwtToken(token);
        loginResponse.setMessage(message);
        return loginResponse;
    }

    private void setAuthorite(LoginResponse loginResponse, List<Role> roles) {
        Set<String> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(role.getRoleName());
        }
        loginResponse.setAuthorities(authorities);
    }
}