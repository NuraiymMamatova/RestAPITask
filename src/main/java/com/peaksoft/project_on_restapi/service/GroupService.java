package com.peaksoft.project_on_restapi.service;

import com.peaksoft.project_on_restapi.dto.request.GroupRequest;
import com.peaksoft.project_on_restapi.dto.response.GroupResponse;
import com.peaksoft.project_on_restapi.model.entity.Group;

import java.io.IOException;
import java.util.List;

public interface GroupService {

    GroupResponse saveGroup(Long courseId, GroupRequest groupRequest);

    GroupResponse deleteGroupById(Long groupId);

    GroupResponse updateGroup(Long groupId, GroupRequest groupRequest);

    GroupResponse findGroupById(Long groupId);

    List<GroupResponse> viewAllGroups();

    void assignGroupToCourse(Long groupId, Long courseId) throws IOException;

}
