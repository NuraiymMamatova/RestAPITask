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
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setEmail(instructorRequest.getEmail());
        instructor.setSpecialization(instructorRequest.getSpecialization());

    }
}
