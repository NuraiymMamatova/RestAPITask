package com.peaksoft.project_on_restapi.service.impl;

import com.peaksoft.project_on_restapi.converter.request.UserRequestConverter;
import com.peaksoft.project_on_restapi.converter.response.UserResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.UserRequest;
import com.peaksoft.project_on_restapi.dto.response.UserResponse;
import com.peaksoft.project_on_restapi.model.entity.Role;
import com.peaksoft.project_on_restapi.model.entity.User;
import com.peaksoft.project_on_restapi.repository.RoleRepository;
import com.peaksoft.project_on_restapi.repository.UserRepository;
import com.peaksoft.project_on_restapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    private final UserRequestConverter userRequestConverter;

    private final UserResponseConverter userResponseConverter;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void init() {
        if (userRepository.countOfUsers() != 0 && roleRepository.countOfRoles() != 0) {

        }else {
            roleRepository.save(new Role("ROLE_STUDENT"));//компанияны , курсту коро алат //task
            roleRepository.save(new Role("ROLE_INSTRUCTOR"));//компанияны, курсту, группаны student task lesson
            roleRepository.save(new Role("ROLE_ADMIN"));//all

            userRepository.save(new User("john@gmail.com", passwordEncoder.encode("john"), "John Travolta"));
            userRepository.save(new User("will@gmail.com", passwordEncoder.encode("will"), "Will Smith"));
            userRepository.save(new User("jim@gmail.com", passwordEncoder.encode("jim"), "Jim Carry"));

            addRoleToUser("john@gmail.com", "ROLE_STUDENT");
            addRoleToUser("will@gmail.com", "ROLE_ADMIN");
            addRoleToUser("jim@gmail.com", "ROLE_INSTRUCTOR");
        }
    }

    @Override
    public UserResponse saveUser(UserRequest request) {
        User user = userRequestConverter.saveUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return userResponseConverter.viewUser(user);
    }

    @Override
    public List<UserResponse> viewAllUser() {
        return userResponseConverter.viewAllUser(userRepository.findAll());
    }

    @Override
    public UserResponse addRoleToUser(String userEmail, String roleName) {
        Role role = roleRepository.findByName(roleName);
        User user = userRepository.findByEmail(userEmail);
        user.addRole(role);
        userRepository.save(user);
        return userResponseConverter.viewUser(user);
    }

    @Override
    public UserResponse deleteUserById(Long userId) {
        User  user = userRepository.findById(userId).get();
        user.getRoles().add(null);
        userRepository.save(user);
        userRepository.delete(user);
        return userResponseConverter.viewUser(user);
    }

    @Override
    public UserResponse updateUser(String userEmail, UserRequest userRequest) {
        User user = userRepository.findByEmail(userEmail);
        userRequestConverter.update(user,userRequest);
        if (userRequest.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
        return userResponseConverter.viewUser(userRepository.save(user));
    }

    @Override
    public UserResponse findUserById(Long userId) {
        return userResponseConverter.viewUser(userRepository.findById(userId).get());
    }

    @Override
    public UserResponse findUserByEmail(String userEmail) {
        return userResponseConverter.viewUser(userRepository.findByEmail(userEmail));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.findByEmail(username) != null) {
            return userRepository.findByEmail(username);
        }
        else {
            throw new UsernameNotFoundException("not found email");
        }
    }



}
// role           class                        access
// student   -   student        read task, read lesson, read companies, read course
// instructor -  instructor     CRUD task/lesson, read/delete student, read course, read companies
// admin     -     user         all

//  |company                  |course                   |group                    |instructor       |student                  |lesson                   |task                     |method
//  |_________________________|_________________________|_________________________|_________________|_________________________|_________________________|_________________________|______
//  |ADMIN                    |ADMIN                    |ADMIN                    |                 |                         |INSTRUCTOR,ADMIN         |INSTRUCTOR,ADMIN         |CREATE
//  |ADMIN,STUDENT,INSTRUCTOR |ADMIN,STUDENT,INSTRUCTOR |ADMIN,STUDENT,INSTRUCTOR |ADMIN,STUDENT    |ADMIN,INSTRUCTOR         |INSTRUCTOR,ADMIN,STUDENT |INSTRUCTOR,ADMIN,STUDENT |READ
//  |ADMIN                    |ADMIN                    |ADMIN                    |INSTRUCTOR       |STUDENT                  |INSTRUCTOR,ADMIN         |INSTRUCTOR,ADMIN         |UPDATE
//  |ADMIN                    |ADMIN                    |ADMIN                    |ADMIN            |ADMIN,INSTRUCTOR         |ADMIN,INSTRUCTOR         |INSTRUCTOR,ADMIN         |DELETE
//  |ASSIGN METHOD NOT EXIST  |ADMIN                    |ADMIN                    |ADMIN,INSTRUCTOR |STUDENT,ADMIN,INSTRUCTOR |ADMIN                    |ASSIGN METHOD NOT EXIST  |ASSIGN
