package com.peaksoft.project_on_restapi.converter.request;

import com.peaksoft.project_on_restapi.dto.request.CompanyRequest;
import com.peaksoft.project_on_restapi.model.entity.Company;
import com.peaksoft.project_on_restapi.model.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CompanyRequestConverter {

    public Company saveCompany(CompanyRequest companyRequest) {
        if (companyRequest == null) {
            return null;
        }
        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
        company.setCourses(companyRequest.getCourses());
        return company;
    }

    public void update(Company company, CompanyRequest companyRequest) {
        if (companyRequest.getCompanyName() != null) {
            company.setCompanyName(companyRequest.getCompanyName());
        }
        if (companyRequest.getLocatedCountry() != null) {
            company.setLocatedCountry(companyRequest.getLocatedCountry());
        }
        if (companyRequest.getCourses() != null) {
            for (Course course1 : company.getCourses()) {
                for (Course course2 : companyRequest.getCourses()) {
                    course1.setCourseName(course2.getCourseName());
                    course1.setDescription(course2.getDescription());
                    course1.setDuration(course2.getDuration());
                }
            }
        }

    }
}
