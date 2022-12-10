package com.peaksoft.project_on_restapi.converter.request;

import com.peaksoft.project_on_restapi.dto.request.InstructorRequest;
import com.peaksoft.project_on_restapi.model.entity.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorRequestConverter {

    public Instructor saveInstructor(InstructorRequest instructorRequest) {
        if (instructorRequest == null) {
            return null;
        }
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setEmail(instructorRequest.getEmail());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        return instructor;
    }

    public void update(Instructor instructor , InstructorRequest instructorRequest) {
        if (instructorRequest.getFirstName() != null) {
            instructor.setFirstName(instructorRequest.getFirstName());
        }
        if (instructorRequest.getLastName() != null) {
            instructor.setLastName(instructorRequest.getLastName());
        }
        if (instructorRequest.getPhoneNumber() != null) {
            instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        }
        if (instructorRequest.getEmail() != null) {
            instructor.setEmail(instructorRequest.getEmail());
        }
        if (instructorRequest.getSpecialization() != null) {
            instructor.setSpecialization(instructorRequest.getSpecialization());
        }

    }
}
