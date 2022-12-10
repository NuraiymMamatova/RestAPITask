package com.peaksoft.project_on_restapi.converter.response;

import com.peaksoft.project_on_restapi.dto.response.StudentResponse;
import com.peaksoft.project_on_restapi.model.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentResponseConverter {

    public StudentResponse viewStudent(Student student) {
        if (student == null) {
            return null;
        }
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setPhoneNumber(student.getPhoneNumber());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setStudyFormat(student.getStudyFormat());
        return studentResponse;
    }

    public List<StudentResponse> viewAllStudent(List<Student> students) {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student : students) {
            studentResponses.add(viewStudent(student));
        }
        return studentResponses;
    }
}
