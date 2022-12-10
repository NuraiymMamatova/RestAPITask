package com.peaksoft.project_on_restapi.converter.request;

import com.peaksoft.project_on_restapi.dto.request.CompanyRequest;
import com.peaksoft.project_on_restapi.model.entity.Company;
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
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
//        for (Course course1 : company.getCourses()) {
//            for (Course course2 : companyRequest.getCourses()) {
//                course1.setCourseName(course2.getCourseName());
//                course1.setDescription(course2.getDescription());
//                course1.setDuration(course2.getDuration());
//                for (Group group1 : course1.getGroups()) {
//                    for (Group group2 : course2.getGroups()) {
//                        group1.setDateOfStart(group2.getDateOfStart());
//                        group1.setGroupName(group2.getGroupName());
//                        group1.setImage(group2.getImage());
//
//                    }
//                }
//            }
//        }
    }
}
