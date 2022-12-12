package com.peaksoft.project_on_restapi.api;

import com.peaksoft.project_on_restapi.dto.request.StudentRequest;
import com.peaksoft.project_on_restapi.dto.response.StudentResponse;
import com.peaksoft.project_on_restapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student_api")
public class StudentApi {

    private final StudentService studentService;

    @PostMapping("/save/{groupId}")
    public StudentResponse saveStudent(@PathVariable Long groupId, @RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(groupId, studentRequest);
    }

    @GetMapping("/all")
    public List<StudentResponse> findAllStudents() {
        return studentService.viewAllStudents();
    }

    @GetMapping("/{studentId}")
    public StudentResponse findById(@PathVariable Long studentId) {
        return studentService.findStudentById(studentId);
    }

    @DeleteMapping("/{studentId}")
    public StudentResponse deleteStudentById(@PathVariable Long studentId) {
        return studentService.deleteStudentById(studentId);
    }

    @PutMapping("/{studentId}")
    public StudentResponse updateStudent(@PathVariable Long studentId,
                                         @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(studentId, studentRequest);
    }

    @PostMapping("/{studentId}/assignStudentToGroup/{groupId}")
    private StudentResponse assignStudentToGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        studentService.assignStudentToGroup(studentId, groupId);
        return studentService.findStudentById(studentId);
    }
}
