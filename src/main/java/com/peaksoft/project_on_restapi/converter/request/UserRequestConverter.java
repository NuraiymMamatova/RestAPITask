package com.peaksoft.project_on_restapi.converter.request;

import com.peaksoft.project_on_restapi.dto.request.UserRequest;
import com.peaksoft.project_on_restapi.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserRequestConverter {

    public User saveUser(UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    public void update(User user, UserRequest userRequest) {
        if (userRequest.getPassword() != null) {
            user.setPassword(userRequest.getPassword());
        }
        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }

    }

}
