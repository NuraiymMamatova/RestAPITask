package com.peaksoft.project_on_restapi.service.impl;

import com.peaksoft.project_on_restapi.converter.request.InstructorRequestConverter;
import com.peaksoft.project_on_restapi.converter.response.InstructorResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.InstructorRequest;
import com.peaksoft.project_on_restapi.dto.request.UserRequest;
import com.peaksoft.project_on_restapi.dto.response.InstructorResponse;
import com.peaksoft.project_on_restapi.dto.response.UserResponse;
import com.peaksoft.project_on_restapi.model.entity.Course;
import com.peaksoft.project_on_restapi.model.entity.Group;
import com.peaksoft.project_on_restapi.model.entity.Instructor;
import com.peaksoft.project_on_restapi.model.entity.Student;
import com.peaksoft.project_on_restapi.repository.CourseRepository;
import com.peaksoft.project_on_restapi.repository.InstructorRepository;
import com.peaksoft.project_on_restapi.service.InstructorService;
import com.peaksoft.project_on_restapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    private final CourseRepository courseRepository;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final InstructorRequestConverter instructorRequestConverter;

    private final InstructorResponseConverter instructorResponseConverter;

    @Override
    public InstructorResponse saveInstructor(Long courseId, InstructorRequest instructorRequest) throws IOException {
        Instructor instructor = instructorRequestConverter.saveInstructor(instructorRequest);
        Course course = courseRepository.findById(courseId).get();
        validator(instructor.getPhoneNumber().replace(" ", ""), instructor.getLastName()
                .replace(" ", ""), instructor.getFirstName()
                .replace(" ", ""));
        //
        if (instructorRepository.findByEmail(instructor.getEmail()) == null && userService.findUserByEmail(instructor.getEmail()) == null) {
            Long count = 0L;
            for (Group group : course.getGroups()) {
                for (Student student : group.getStudents()) {
                    count++;

                }
            }
            instructor.setCount(count);
            //
            UserResponse user = userService.saveUser(new UserRequest(instructor.getEmail(), instructor.getPassword()));
            userService.addRoleToUser(user.getEmail(), "ROLE_STUDENT");
            course.addInstructor(instructor);
            instructor.setCourse(course);
            String encodePassword = passwordEncoder.encode(instructor.getPassword());
            instructor.setPassword(encodePassword);
            instructorRepository.save(instructor);
            return instructorResponseConverter.viewInstructor(instructor);
        } else {
            throw new IOException("Instructor with this email already exists!!!");
        }
    }

    @Override
    public InstructorResponse deleteInstructorById(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        UserResponse user = userService.findUserByEmail(instructor .getEmail());
        userService.deleteUserById(Long.valueOf(user.getId()));
        instructorRepository.delete(instructor);
        return instructorResponseConverter.viewInstructor(instructor);
    }

    @Override
    public InstructorResponse updateInstructor(Long instructorId, InstructorRequest instructorRequest) throws IOException {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        validator(instructor.getPhoneNumber().replace(" ", ""), instructor.getLastName()
                .replace(" ", ""), instructor.getFirstName()
                .replace(" ", ""));
        if (userService.findUserByEmail(instructor.getEmail()) != null || instructorRepository.findByEmail(instructor.getEmail()) != null) {
            userService.updateUser(instructor.getEmail(), new UserRequest(instructorRequest.getEmail(), instructorRequest.getPassword()));

            String encodePassword = passwordEncoder.encode(instructorRequest.getPassword());
            instructor.setPassword(encodePassword);
            instructorRequestConverter.update(instructor, instructorRequest);
            return instructorResponseConverter.viewInstructor(instructorRepository.save(instructor));
        }else {
            throw new IOException("Instructor with this email not found !!!");
        }

    }

    @Override
    public InstructorResponse findInstructorById(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        return instructorResponseConverter.viewInstructor(instructor);
    }

    @Override
    public List<InstructorResponse> viewAllInstructors() {
        return instructorResponseConverter.viewAllInstructor(instructorRepository.findAll());
    }

    @Override
    public List<InstructorResponse> viewAllInstructors(Long courseId) {
        return instructorResponseConverter.viewAllInstructor(instructorRepository.getAllInstructorByCourseId(courseId));
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) {
        if (instructorId != null) {
            Instructor instructor = instructorRepository.findById(instructorId).get();
            if (courseId != null) {
                Course course = courseRepository.findById(courseId).get();
                //
                Long count = 0L;
                for (Group group : course.getGroups()) {
                    for (Student student : group.getStudents()) {
                        count++;
                    }
                }
                instructor.setCount(count);
                //
                instructor.setCourse(course);
                course.addInstructor(instructor);
                instructorRepository.save(instructor);
            } else {
                System.out.println("course is null");
            }
        } else {
            System.out.println("instructor is null");
        }
    }

    private void validator(String phoneNumber, String firstName, String lastName) throws IOException {
        if (firstName.length() > 2 && lastName.length() > 2) {
            for (Character i : firstName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("В имени инструктора нельзя вставлять цифры");
                }
            }

            for (Character i : lastName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("В фамилию инструктора нельзя вставлять цифры");
                }
            }
        } else {
            throw new IOException("В имени или фамилии инструктора должно быть как минимум 3 буквы");
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

