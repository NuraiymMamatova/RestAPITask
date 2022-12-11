package com.peaksoft.project_on_restapi.service;

import com.peaksoft.project_on_restapi.dto.request.StudentRequest;
import com.peaksoft.project_on_restapi.dto.response.StudentResponse;
import com.peaksoft.project_on_restapi.model.entity.Student;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    StudentResponse saveStudent(StudentRequest studentRequest);

    StudentResponse saveStudent(Long groupId, StudentRequest studentRequest);

    StudentResponse deleteStudentById(Long studentId);

    StudentResponse updateStudent(Long studentId, StudentRequest studentRequest);

    StudentResponse findStudentById(Long studentId);

    List<StudentResponse> viewAllStudents();

    List<Student> getAllStudents(Long id);

    void assignStudentToGroup(Long studentId, Long groupId);

}
