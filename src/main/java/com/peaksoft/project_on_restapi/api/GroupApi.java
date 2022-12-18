package com.peaksoft.project_on_restapi.api;

import com.peaksoft.project_on_restapi.dto.request.GroupRequest;
import com.peaksoft.project_on_restapi.dto.response.GroupResponse;
import com.peaksoft.project_on_restapi.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group_api")
public class GroupApi {

    private final GroupService groupService;

    @PostMapping("/save/{courseId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GroupResponse saveGroup(@PathVariable Long courseId, @RequestBody GroupRequest groupRequest) {
        return groupService.saveGroup(courseId, groupRequest);
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public List<GroupResponse> findAllGroups() {
        return groupService.viewAllGroups();
    }

    @GetMapping("/{groupId}")
    @PreAuthorize("isAuthenticated()")
    public GroupResponse findById(@PathVariable Long groupId) {
        return groupService.findGroupById(groupId);
    }

    @DeleteMapping("/{groupId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GroupResponse deleteGroupById(@PathVariable Long groupId) {
        return groupService.deleteGroupById(groupId);
    }

    @PutMapping("/{groupId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GroupResponse updateGroup(@PathVariable Long groupId,
                                     @RequestBody GroupRequest groupRequest) {
        return groupService.updateGroup(groupId, groupRequest);
    }
}
