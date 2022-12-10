package com.peaksoft.project_on_restapi.converter.request;

import com.peaksoft.project_on_restapi.dto.request.GroupRequest;
import com.peaksoft.project_on_restapi.model.entity.Group;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class GroupRequestConverter {

    public Group saveGroup(GroupRequest groupRequest) {
        if (groupRequest == null) {
            return null;
        }
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setDateOfStart(Date.valueOf(LocalDate.now()));
        group.setImage(groupRequest.getImage());
        return group;
    }

    public void update(Group group, GroupRequest groupRequest) {
        if (groupRequest.getGroupName() != null) {
            group.setGroupName(groupRequest.getGroupName());
        }
        if (groupRequest.getImage() != null) {
            group.setImage(groupRequest.getImage());
        }

    }
}
