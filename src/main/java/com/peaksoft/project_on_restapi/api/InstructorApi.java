package com.peaksoft.project_on_restapi.api;

import com.peaksoft.project_on_restapi.converter.request.InstructorRequestConverter;
import com.peaksoft.project_on_restapi.dto.request.InstructorRequest;
import com.peaksoft.project_on_restapi.dto.response.CompanyResponse;
import com.peaksoft.project_on_restapi.dto.response.InstructorResponse;
import com.peaksoft.project_on_restapi.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor_api")
public class InstructorApi {

    private final InstructorService instructorService;

    @PostMapping("/save")
    public InstructorResponse saveInstructor(@RequestBody InstructorRequest instructorRequest) {
        return instructorService.saveInstructor(instructorRequest);
    }

    @PostMapping("/save/{courseId}")
    public InstructorResponse saveInstructor(@PathVariable Long courseId, @RequestBody InstructorRequest instructorRequest) {
        return instructorService.saveInstructor(courseId, instructorRequest);
    }

    @GetMapping("/all")
    public List<InstructorResponse> findAllInstructors() {
        return instructorService.viewAllInstructors();
    }

    @GetMapping("/{instructorId}")
    public InstructorResponse findById(@PathVariable Long instructorId) {
        return instructorService.findInstructorById(instructorId);
    }

    @DeleteMapping("/{instructorId}")
    public InstructorResponse deleteInstructorById(@PathVariable Long instructorId) {
        return instructorService.deleteInstructorById(instructorId);
    }

    @PutMapping("/{instructorId}")
    public InstructorResponse updateInstructor(@PathVariable Long instructorId,
                                               @RequestBody InstructorRequest instructorRequest) {
        return instructorService.updateInstructor(instructorId, instructorRequest);
        
    }

    @PostMapping("/{instructorId}/assignInstructorToCourse/{courseId}")
    private InstructorResponse assignInstructorToCourse(@PathVariable Long instructorId,
                                                        @PathVariable Long courseId) {
        instructorService.assignInstructorToCourse(instructorId, courseId);
        return instructorService.findInstructorById(instructorId);

    }

}
