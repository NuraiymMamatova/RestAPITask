package com.peaksoft.project_on_restapi.api;

import com.peaksoft.project_on_restapi.converter.response.CompanyResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.CompanyRequest;
import com.peaksoft.project_on_restapi.dto.response.CompanyResponse;
import com.peaksoft.project_on_restapi.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company_api")
public class CompanyApi {

    private final CompanyService companyService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CompanyResponse saveCompany(@RequestBody CompanyRequest companyRequest) {
        return companyService.saveCompany(companyRequest);
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public CompanyResponseConverter findAllCompanies(@RequestParam(name = "name", required = false) String name,
                                                     @RequestParam int page,
                                                     @RequestParam int size) {
        return companyService.getAll(name, page, size);
    }

    @GetMapping("/{companyId}")
    @PreAuthorize("isAuthenticated()")
    public CompanyResponse findById(@PathVariable Long companyId) {
        return companyService.findCompanyById(companyId);
    }

    @DeleteMapping("/{companyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CompanyResponse deleteCompanyById(@PathVariable Long companyId) {
        return companyService.deleteCompanyById(companyId);
    }

    @PutMapping("/{companyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CompanyResponse updateCompany(@PathVariable Long companyId,
                                         @RequestBody CompanyRequest companyRequest) {
        return companyService.updateCompany(companyId, companyRequest);
    }

}
