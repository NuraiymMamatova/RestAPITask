package com.peaksoft.project_on_restapi.service.impl;

import com.peaksoft.project_on_restapi.converter.request.GroupRequestConverter;
import com.peaksoft.project_on_restapi.converter.response.GroupResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.GroupRequest;
import com.peaksoft.project_on_restapi.dto.response.GroupResponse;
import com.peaksoft.project_on_restapi.model.entity.Group;
import com.peaksoft.project_on_restapi.repository.GroupRepository;
import com.peaksoft.project_on_restapi.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final GroupRequestConverter groupRequestConverter;

    private final GroupResponseConverter groupResponseConverter;

    @Override
    public GroupResponse saveGroup(GroupRequest groupRequest) {
        Group group = groupRequestConverter.saveGroup(groupRequest);
        groupRepository.save(group);
        return groupResponseConverter.viewGroup(group);
    }

    @Override
    public GroupResponse deleteGroupById(Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        groupRepository.delete(group);
        return groupResponseConverter.viewGroup(group);
    }

    @Override
    public GroupResponse updateGroup(Long groupId, GroupRequest groupRequest) {
        Group group = groupRepository.findById(groupId).get();
        groupRequestConverter.update(group, groupRequest);
        return groupResponseConverter.viewGroup(groupRepository.save(group));
    }

    @Override
    public GroupResponse findGroupById(Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        return groupResponseConverter.viewGroup(group);
    }

    @Override
    public List<GroupResponse> viewAllGroups() {
        return groupResponseConverter.viewAllGroup(groupRepository.findAll());
    }

    @Override
    public void assignGroupToCourse(Long groupId, Long courseId) throws IOException {

    }
}
