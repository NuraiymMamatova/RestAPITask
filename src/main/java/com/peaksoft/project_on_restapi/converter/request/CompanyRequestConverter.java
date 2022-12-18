package com.peaksoft.project_on_restapi.converter.request;

import com.peaksoft.project_on_restapi.dto.request.CompanyRequest;
import com.peaksoft.project_on_restapi.model.entity.Company;
import com.peaksoft.project_on_restapi.model.entity.Course;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyRequestConverter {

    public Company saveCompany(CompanyRequest companyRequest) {
        if (companyRequest == null) {
            return null;
        }
        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
        return company;
    }

    public void update(Company company, CompanyRequest companyRequest) {
        if (companyRequest.getCompanyName() != null) {
            company.setCompanyName(companyRequest.getCompanyName());
        }
        if (companyRequest.getLocatedCountry() != null) {
            company.setLocatedCountry(companyRequest.getLocatedCountry());
        }

    }
}
