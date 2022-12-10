package com.peaksoft.project_on_restapi.converter.response;

import com.peaksoft.project_on_restapi.dto.response.CompanyResponse;
import com.peaksoft.project_on_restapi.model.entity.Company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyResponseConverter {

    public CompanyResponse viewCompany(Company company) {
        if (company == null) {
            return null;
        }
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setId(company.getId());
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setLocatedCountry(company.getLocatedCountry());
//        companyResponse.setCourses(company.getCourses());
        return companyResponse;
    }

    public List<CompanyResponse> viewAllCompany(List<Company> companies) {
        List<CompanyResponse> companyResponses = new ArrayList<>();
        for (Company company : companies) {
            companyResponses.add(viewCompany(company));
        }
        return companyResponses;
    }

}
