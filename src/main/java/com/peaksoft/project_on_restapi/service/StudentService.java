package com.peaksoft.project_on_restapi.service;

import com.peaksoft.project_on_restapi.converter.response.StudentResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.StudentRequest;
import com.peaksoft.project_on_restapi.dto.response.StudentResponse;
import com.peaksoft.project_on_restapi.model.entity.Student;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    StudentResponse saveStudent(Long groupId, StudentRequest studentRequest) throws IOException;

    StudentResponse deleteStudentById(Long studentId);

    StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) throws IOException;

    StudentResponse findStudentById(Long studentId);

    List<StudentResponse> viewAllStudents();

    List<StudentResponse> viewAllStudents(Long studentId);

    void assignStudentToGroup(Long studentId, Long groupId) throws IOException;

    StudentResponseConverter getAll(String email, int page, int size);

    List<StudentResponse> viewPagination(List<Student> students);

    List<Student> search(String firstName, Pageable pageable);

}
