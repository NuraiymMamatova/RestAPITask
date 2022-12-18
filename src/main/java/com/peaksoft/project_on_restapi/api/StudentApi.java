package com.peaksoft.project_on_restapi.api;

import com.peaksoft.project_on_restapi.dto.request.StudentRequest;
import com.peaksoft.project_on_restapi.dto.response.StudentResponse;
import com.peaksoft.project_on_restapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student_api")
public class StudentApi {

    private final StudentService studentService;

    @PostMapping("/save/{groupId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public StudentResponse saveStudent(@PathVariable Long groupId, @RequestBody StudentRequest studentRequest) throws IOException {
        return studentService.saveStudent(groupId, studentRequest);
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public List<StudentResponse> findAllStudents() {
        return studentService.viewAllStudents();
    }

    @GetMapping("/all/{groupId}")
    @PreAuthorize("isAuthenticated()")
    public List<StudentResponse> findAllStudents(@PathVariable Long groupId) {
        return studentService.viewAllStudents(groupId);
    }

    @GetMapping("/{studentId}")
    @PreAuthorize("isAuthenticated()")
    public StudentResponse findById(@PathVariable Long studentId) {
        return studentService.findStudentById(studentId);
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public StudentResponse deleteStudentById(@PathVariable Long studentId) {
        return studentService.deleteStudentById(studentId);
    }

    @PutMapping("/{studentId}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public StudentResponse updateStudent(@PathVariable Long studentId,
                                         @RequestBody StudentRequest studentRequest) throws IOException {
        return studentService.updateStudent(studentId, studentRequest);
    }

    @PostMapping("/{studentId}/assignStudentToGroup/{groupId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public StudentResponse assignStudentToGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        studentService.assignStudentToGroup(studentId, groupId);
        return studentService.findStudentById(studentId);
    }
}
