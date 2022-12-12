package com.peaksoft.project_on_restapi.service;

import com.peaksoft.project_on_restapi.dto.request.InstructorRequest;
import com.peaksoft.project_on_restapi.dto.response.InstructorResponse;
import com.peaksoft.project_on_restapi.model.entity.Instructor;

import java.io.IOException;
import java.util.List;

public interface InstructorService {

    InstructorResponse saveInstructor(Long courseId, InstructorRequest instructorRequest);

    InstructorResponse deleteInstructorById(Long instructorId);

    InstructorResponse updateInstructor(Long instructorId, InstructorRequest instructorRequest);

    InstructorResponse findInstructorById(Long instructorId);

    List<InstructorResponse> viewAllInstructors();

    List<Instructor> getAllInstructors(Long id);

    void assignInstructorToCourse(Long instructorId, Long courseId);

}
