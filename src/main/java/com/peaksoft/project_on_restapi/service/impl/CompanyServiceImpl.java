package com.peaksoft.project_on_restapi.service.impl;

import com.peaksoft.project_on_restapi.converter.request.CompanyRequestConverter;
import com.peaksoft.project_on_restapi.converter.response.CompanyResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.CompanyRequest;
import com.peaksoft.project_on_restapi.dto.response.CompanyResponse;
import com.peaksoft.project_on_restapi.model.entity.Company;
import com.peaksoft.project_on_restapi.repository.CompanyRepository;
import com.peaksoft.project_on_restapi.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyRequestConverter companyRequestConverter;

    private final CompanyResponseConverter companyResponseConverter;

    @Override
    public CompanyResponse saveCompany(CompanyRequest companyRequest) {
        Company company = companyRequestConverter.saveCompany(companyRequest);
        companyRepository.save(company);
        return companyResponseConverter.viewCompany(company);
    }

    @Override
    public CompanyResponse deleteCompanyById(Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        companyRepository.delete(company);
        return companyResponseConverter.viewCompany(company);
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(companyId).get();
        companyRequestConverter.update(company, companyRequest);
        return companyResponseConverter.viewCompany(companyRepository.save(company));
    }

    @Override
    public CompanyResponse findCompanyById(Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        return companyResponseConverter.viewCompany(company);
    }

    @Override
    public List<CompanyResponse> viewAllCompanies() {
        return companyResponseConverter.viewAllCompany(companyRepository.findAll());
    }
}
