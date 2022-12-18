package com.peaksoft.project_on_restapi.converter.request;

import com.peaksoft.project_on_restapi.dto.request.StudentRequest;
import com.peaksoft.project_on_restapi.model.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentRequestConverter {

    public Student saveStudent(StudentRequest studentRequest) {
        if (studentRequest == null) {
            return null;
        }
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setPassword(studentRequest.getPassword());
        student.setStudyFormat(studentRequest.getStudyFormat());
        return student;
    }

    public void update(Student student, StudentRequest studentRequest) {
        if (studentRequest.getFirstName() != null) {
            student.setFirstName(studentRequest.getFirstName());
        }
        if (studentRequest.getLastName() != null) {
            student.setLastName(studentRequest.getLastName());
        }
        if (studentRequest.getPhoneNumber() != null) {
            student.setPhoneNumber(studentRequest.getPhoneNumber());
        }
        if (studentRequest.getEmail() != null) {
            student.setEmail(studentRequest.getEmail());
        }
        if (studentRequest.getStudyFormat() != null) {
            student.setStudyFormat(studentRequest.getStudyFormat());
        }

    }
}
