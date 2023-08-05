package com.peaksoft.project_on_restapi.converter.response;

import com.peaksoft.project_on_restapi.dto.response.CompanyResponse;
import com.peaksoft.project_on_restapi.model.entity.Company;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class CompanyResponseConverter {

    private List<CompanyResponse> companyResponseList;

    public CompanyResponse viewCompany(Company company) {
        if (company == null) {
            return null;
        }
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setId(company.getId());
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setLocatedCountry(company.getLocatedCountry());
        companyResponse.setCount(company.getCount());
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
