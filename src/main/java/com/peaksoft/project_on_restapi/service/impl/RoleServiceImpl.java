package com.peaksoft.project_on_restapi.service.impl;

import com.peaksoft.project_on_restapi.model.entity.Role;
import com.peaksoft.project_on_restapi.repository.RoleRepository;
import com.peaksoft.project_on_restapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

}
