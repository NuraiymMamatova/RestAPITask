package com.peaksoft.project_on_restapi.api;

import com.peaksoft.project_on_restapi.converter.response.InstructorResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.InstructorRequest;
import com.peaksoft.project_on_restapi.dto.response.InstructorResponse;
import com.peaksoft.project_on_restapi.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor_api")
public class InstructorApi {

    private final InstructorService instructorService;

    @PostMapping("/save/{courseId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public InstructorResponse saveInstructor(@PathVariable Long courseId, @RequestBody InstructorRequest instructorRequest) throws IOException {
        return instructorService.saveInstructor(courseId, instructorRequest);
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public InstructorResponseConverter findAllInstructors(@RequestParam(name = "name", required = false) String name,
                                                          @RequestParam int page,
                                                          @RequestParam int size) {
        return instructorService.getAll(name, page, size);
    }

    @GetMapping("/all/{courseId}")
    @PreAuthorize("isAuthenticated()")
    public List<InstructorResponse> findAllInstructors(@PathVariable Long courseId) {
        return instructorService.viewAllInstructors(courseId);
    }

    @GetMapping("/{instructorId}")
    @PreAuthorize("isAuthenticated()")
    public InstructorResponse findById(@PathVariable Long instructorId) {
        return instructorService.findInstructorById(instructorId);
    }

    @DeleteMapping("/{instructorId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public InstructorResponse deleteInstructorById(@PathVariable Long instructorId) {
        return instructorService.deleteInstructorById(instructorId);
    }

    @PutMapping("/{instructorId}")
    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    public InstructorResponse updateInstructor(@PathVariable Long instructorId,
                                               @RequestBody InstructorRequest instructorRequest) throws IOException {
        return instructorService.updateInstructor(instructorId, instructorRequest);
        
    }

    @PostMapping("/{instructorId}/assignInstructorToCourse/{courseId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public InstructorResponse assignInstructorToCourse(@PathVariable Long instructorId,
                                                        @PathVariable Long courseId) throws IOException {
        instructorService.assignInstructorToCourse(instructorId, courseId);
        return instructorService.findInstructorById(instructorId);

    }

}
