package com.peaksoft.project_on_restapi.service.impl;

import com.peaksoft.project_on_restapi.converter.request.InstructorRequestConverter;
import com.peaksoft.project_on_restapi.converter.response.InstructorResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.InstructorRequest;
import com.peaksoft.project_on_restapi.dto.response.InstructorResponse;
import com.peaksoft.project_on_restapi.model.entity.Instructor;
import com.peaksoft.project_on_restapi.repository.InstructorRepository;
import com.peaksoft.project_on_restapi.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    private final InstructorRequestConverter instructorRequestConverter;

    private final InstructorResponseConverter instructorResponseConverter;

    @Override
    public InstructorResponse saveInstructor(InstructorRequest instructorRequest) {
        Instructor instructor = instructorRequestConverter.saveInstructor(instructorRequest);
        instructorRepository.save(instructor);
        return instructorResponseConverter.viewInstructor(instructor);
    }

    @Override
    public InstructorResponse deleteInstructorById(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        instructorRepository.delete(instructor);
        return instructorResponseConverter.viewInstructor(instructor);
    }

    @Override
    public InstructorResponse updateInstructor(Long instructorId, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        instructorRequestConverter.update(instructor, instructorRequest);
        return instructorResponseConverter.viewInstructor(instructorRepository.save(instructor));
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
    public List<Instructor> getAllInstructors(Long id) {
        return null;
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) throws IOException {

    }
}
