package com.peaksoft.project_on_restapi.service;

import com.peaksoft.project_on_restapi.dto.request.UserRequest;
import com.peaksoft.project_on_restapi.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse saveUser(UserRequest request);

    List<UserResponse> viewAllUser();

    UserResponse addRoleToUser(String userEmail, String roleName);

    UserResponse deleteUserById(Long userId);

    UserResponse updateUser(String userEmail, UserRequest userRequest);

    UserResponse findUserById(Long userId);

    UserResponse findUserByEmail(String userEmail);

}
