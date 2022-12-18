package com.peaksoft.project_on_restapi.converter.response;

import com.peaksoft.project_on_restapi.dto.response.UserResponse;
import com.peaksoft.project_on_restapi.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserResponseConverter {

    public UserResponse viewUser(User user) {
        if (user == null) {
            return null;
        }
        UserResponse userResponse = new UserResponse();
        if (user.getId() != null) {
            userResponse.setId(String.valueOf(user.getId()));
        }
        userResponse.setFirstName(user.getFirstName());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }

    public List<UserResponse> viewAllUser(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(viewUser(user));
        }
        return userResponses;
    }
}
