package com.peaksoft.project_on_restapi.service.impl;

import com.peaksoft.project_on_restapi.converter.request.CompanyRequestConverter;
import com.peaksoft.project_on_restapi.converter.response.CompanyResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.CompanyRequest;
import com.peaksoft.project_on_restapi.dto.response.CompanyResponse;
import com.peaksoft.project_on_restapi.dto.response.UserResponse;
import com.peaksoft.project_on_restapi.model.entity.*;
import com.peaksoft.project_on_restapi.repository.CompanyRepository;
import com.peaksoft.project_on_restapi.service.CompanyService;
import com.peaksoft.project_on_restapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final UserService userService;

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
        for (Course course : company.getCourses()) {
            for (Group group : course.getGroups()) {
                if (group.getStudents() != null) {
                    System.out.println("if group.getStudents != null 1");
                    for (Student student : group.getStudents()) {
                        System.out.println("if group.getStudents != null 2");
                        if (student.getEmail() == null) {
                            System.out.println("student is null");
                        } else {
                            UserResponse user = userService.findUserByEmail(student.getEmail());
                            if (user == null) {
                                System.out.println("user is null");
                            }else {
                                if (user.getId() != null) {
                                    System.out.println("if user.getId != null 1");
                                    userService.deleteUserById(Long.valueOf(user.getId()));
                                    System.out.println("if user.getId != null 2");
                                }
                            }
                        }
                    }
                }
            }
            for (Instructor instructor : course.getInstructors()) {
                UserResponse user = userService.findUserByEmail(instructor.getEmail());
                if (user == null) {
                    System.out.println("user is null");
                }else {
                    userService.deleteUserById(Long.valueOf(user.getId()));
                }
            }
        }
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

    @Override
    public CompanyResponseConverter getAll(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        companyResponseConverter.setCompanyResponseList(viewPagination(search(name, pageable)));
        return companyResponseConverter;
    }

    @Override
    public List<CompanyResponse> viewPagination(List<Company> companies) {
        List<CompanyResponse> companyResponseList = new ArrayList<>();
        for (Company company : companies) {
            companyResponseList.add(companyResponseConverter.viewCompany(company));
        }
        return companyResponseList;
    }

    @Override
    public List<Company> search(String name, Pageable pageable) {
        String companyName = name == null ? "" : name;
        return companyRepository.searchPagination(companyName.toUpperCase(), pageable);
    }
}
