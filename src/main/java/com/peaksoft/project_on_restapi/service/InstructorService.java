package com.peaksoft.project_on_restapi.service;

import com.peaksoft.project_on_restapi.dto.request.InstructorRequest;
import com.peaksoft.project_on_restapi.dto.response.InstructorResponse;

import java.io.IOException;
import java.util.List;

public interface InstructorService {

    InstructorResponse saveInstructor(Long courseId, InstructorRequest instructorRequest) throws IOException;

    InstructorResponse deleteInstructorById(Long instructorId);

    InstructorResponse updateInstructor(Long instructorId, InstructorRequest instructorRequest) throws IOException;

    InstructorResponse findInstructorById(Long instructorId);

    List<InstructorResponse> viewAllInstructors();

    List<InstructorResponse> viewAllInstructors(Long courseId);

    void assignInstructorToCourse(Long instructorId, Long courseId) throws IOException;

}
