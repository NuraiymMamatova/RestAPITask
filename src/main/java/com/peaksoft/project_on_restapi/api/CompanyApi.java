package com.peaksoft.project_on_restapi.api;

import com.peaksoft.project_on_restapi.dto.request.CompanyRequest;
import com.peaksoft.project_on_restapi.dto.response.CompanyResponse;
import com.peaksoft.project_on_restapi.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company_api")
public class CompanyApi {

    private final CompanyService companyService;

    @PostMapping("/save")
    public CompanyResponse saveCompany(@RequestBody CompanyRequest companyRequest) {
        return companyService.saveCompany(companyRequest);
    }

    @GetMapping("/all")
    public List<CompanyResponse> findAllCompanies() {
        return companyService.viewAllCompanies();
    }

    @GetMapping("/{companyId}")
    public CompanyResponse findById(@PathVariable Long companyId) {
        return companyService.findCompanyById(companyId);
    }

    @DeleteMapping("/{companyId}")
    public CompanyResponse deleteCompanyById(@PathVariable Long companyId) {
        return companyService.deleteCompanyById(companyId);
    }

    @PutMapping("/{companyId}")
    public CompanyResponse updateCompany(@PathVariable Long companyId,
                                         @RequestBody CompanyRequest companyRequest) {
        return companyService.updateCompany(companyId, companyRequest);
    }
}
