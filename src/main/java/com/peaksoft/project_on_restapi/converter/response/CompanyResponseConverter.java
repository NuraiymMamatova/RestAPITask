package com.peaksoft.project_on_restapi.converter.response;

import com.peaksoft.project_on_restapi.dto.response.CompanyResponse;
import com.peaksoft.project_on_restapi.model.entity.Company;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyResponseConverter {

    public CompanyResponse viewCompany(Company company) {
        System.out.println("view 0");
        if (company == null) {
            System.out.println("view 1");
            return null;
        }
        System.out.println("view 2");
        CompanyResponse companyResponse = new CompanyResponse();
        System.out.println("view 3");
        companyResponse.setId(company.getId());
        System.out.println("view 4");
        companyResponse.setCompanyName(company.getCompanyName());
        System.out.println("view 5");
        companyResponse.setLocatedCountry(company.getLocatedCountry());
        System.out.println("view 6");
        companyResponse.setCourses(company.getCourses());
        System.out.println("view 7");
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
