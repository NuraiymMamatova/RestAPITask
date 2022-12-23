package com.peaksoft.project_on_restapi.converter.response;

import com.peaksoft.project_on_restapi.dto.response.InstructorResponse;
import com.peaksoft.project_on_restapi.model.entity.Instructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class InstructorResponseConverter {

    private List<InstructorResponse> instructorResponseList;

    public InstructorResponse viewInstructor(Instructor instructor) {
        if (instructor == null) {
            return null;
        }
        InstructorResponse instructorResponse = new InstructorResponse();
        instructorResponse.setId(instructor.getId());
        instructorResponse.setFirstName(instructor.getFirstName());
        instructorResponse.setLastName(instructor.getLastName());
        instructorResponse.setPhoneNumber(instructor.getPhoneNumber());
        instructorResponse.setEmail(instructor.getEmail());
        instructorResponse.setSpecialization(instructor.getSpecialization());
        instructorResponse.setCount(instructor.getCount());
        return instructorResponse;
    }

    public List<InstructorResponse> viewAllInstructor(List<Instructor> instructors) {
        List<InstructorResponse> instructorResponses = new ArrayList<>();
        for (Instructor instructor : instructors) {
            instructorResponses.add(viewInstructor(instructor));
        }
        return instructorResponses;
    }
}
