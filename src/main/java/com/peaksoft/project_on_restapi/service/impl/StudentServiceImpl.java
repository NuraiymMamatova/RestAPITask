package com.peaksoft.project_on_restapi.service.impl;

import com.peaksoft.project_on_restapi.converter.request.StudentRequestConverter;
import com.peaksoft.project_on_restapi.converter.response.StudentResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.StudentRequest;
import com.peaksoft.project_on_restapi.dto.request.UserRequest;
import com.peaksoft.project_on_restapi.dto.response.StudentResponse;
import com.peaksoft.project_on_restapi.dto.response.UserResponse;
import com.peaksoft.project_on_restapi.model.entity.*;
import com.peaksoft.project_on_restapi.repository.GroupRepository;
import com.peaksoft.project_on_restapi.repository.StudentRepository;
import com.peaksoft.project_on_restapi.repository.UserRepository;
import com.peaksoft.project_on_restapi.service.StudentService;
import com.peaksoft.project_on_restapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final GroupRepository groupRepository;

    private final UserService userService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final StudentRequestConverter studentRequestConverter;

    private final StudentResponseConverter studentResponseConverter;

    @Override
    public StudentResponse saveStudent(Long groupId, StudentRequest studentRequest) throws IOException {
        Student student = studentRequestConverter.saveStudent(studentRequest);
        Group group = groupRepository.findById(groupId).get();
        validator(student.getPhoneNumber().replace(" ", ""), student.getLastName()
                .replace(" ", ""), student.getFirstName()
                .replace(" ", ""));
        if (userService.findUserByEmail(student.getEmail()) == null && studentRepository.findByEmail(student.getEmail()) == null) {
            //
            for (Course course : group.getCourses()) {
                course.getCompany().plus();
            }
            for (Course course : group.getCourses()) {
                for (Instructor instructor : course.getInstructors()) {
                    instructor.plus();
                }
            }
            //
            System.out.println("save user 1");
            UserResponse user = userService.saveUser(new UserRequest(student.getEmail(), student.getPassword()));
            userService.addRoleToUser(user.getEmail(), "ROLE_STUDENT");
            group.addStudents(student);
            student.setGroup(group);
            String encodePassword = passwordEncoder.encode(student.getPassword());
            student.setPassword(encodePassword);
            studentRepository.save(student);
            return studentResponseConverter.viewStudent(student);
        } else {
            throw new IOException("Student with this email already exists!!!");
        }
    }

    @Override
    public StudentResponse deleteStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        //
        for (Course course : student.getGroup().getCourses()) {
            course.getCompany().minus();
        }
        for (Course course : student.getGroup().getCourses()) {
            for (Instructor instructor : course.getInstructors()) {
                instructor.minus();
            }
        }
        UserResponse user = userService.findUserByEmail(student.getEmail());
        userService.deleteUserById(Long.valueOf(user.getId()));
        // 
        studentRepository.delete(student);
        return studentResponseConverter.viewStudent(student);
    }

    @Override
    public StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) throws IOException {
        Student student = studentRepository.findById(studentId).get();
        validator(student.getPhoneNumber().replace(" ", ""), student.getLastName()
                .replace(" ", ""), student.getFirstName()
                .replace(" ", ""));
        if (userService.findUserByEmail(student.getEmail()) != null || studentRepository.findByEmail(student.getEmail()) != null) {
            userService.updateUser(student.getEmail(), new UserRequest(studentRequest.getEmail(), studentRequest.getPassword()));
            String encodePassword = passwordEncoder.encode(studentRequest.getPassword());
            studentRequest.setPassword(encodePassword);
            student.setPassword(encodePassword);
            studentRequestConverter.update(student, studentRequest);
            return studentResponseConverter.viewStudent(studentRepository.save(student));
        } else {
            throw new IOException("Student with this email not found!!!");
        }

    }

    @Override
    public StudentResponse findStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        return studentResponseConverter.viewStudent(student);
    }

    @Override
    public List<StudentResponse> viewAllStudents() {
        return studentResponseConverter.viewAllStudent(studentRepository.findAll());
    }

    @Override
    public List<StudentResponse> viewAllStudents(Long groupId) {
        return studentResponseConverter.viewAllStudent(studentRepository.getAllStudentsByGroupId(groupId));
    }

    @Override
    public void assignStudentToGroup(Long studentId, Long groupId) {
        if (studentId != null) {
            Student student = studentRepository.findById(studentId).get();
            if (groupId != null) {
                Group group = groupRepository.findById(groupId).get();
                student.setGroup(group);
                group.addStudents(student);
                groupRepository.save(group);
                studentRepository.save(student);
            }
        }
    }

    private void validator(String phoneNumber, String firstName, String lastName) throws IOException {
        if (firstName.length() > 2 && lastName.length() > 2) {
            for (Character i : firstName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("В имени студента нельзя вставлять цифры");
                }
            }

            for (Character i : lastName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("В фамилию студента нельзя вставлять цифры");
                }
            }
        } else {
            throw new IOException("В имени или фамилии студента должно быть как минимум 3 буквы");
        }

        if (phoneNumber.length() == 13
                && phoneNumber.charAt(0) == '+'
                && phoneNumber.charAt(1) == '9'
                && phoneNumber.charAt(2) == '9'
                && phoneNumber.charAt(3) == '6') {
            int counter = 0;

            for (Character i : phoneNumber.toCharArray()) {
                if (counter != 0) {
                    if (!Character.isDigit(i)) {
                        throw new IOException("Формат номера не правильный");
                    }
                }
                counter++;
            }
        } else {
            throw new IOException("Формат номера не правильный");
        }
    }
}
