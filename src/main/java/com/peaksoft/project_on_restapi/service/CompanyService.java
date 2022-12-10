package com.peaksoft.project_on_restapi.service;

import com.peaksoft.project_on_restapi.dto.request.CompanyRequest;
import com.peaksoft.project_on_restapi.dto.response.CompanyResponse;

import java.util.List;

public interface CompanyService {

    CompanyResponse saveCompany(CompanyRequest companyRequest);

    CompanyResponse deleteCompanyById(Long companyId);

    CompanyResponse updateCompany(Long companyId, CompanyRequest companyRequest);

    CompanyResponse findCompanyById(Long companyId);

    List<CompanyResponse> viewAllCompanies();

}
