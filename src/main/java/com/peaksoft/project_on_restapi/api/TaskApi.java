package com.peaksoft.project_on_restapi.api;

import com.peaksoft.project_on_restapi.dto.request.TaskRequest;
import com.peaksoft.project_on_restapi.dto.response.TaskResponse;
import com.peaksoft.project_on_restapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task_api")
public class TaskApi {

    private final TaskService taskService;

    @PostMapping("/save/{lessonId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public TaskResponse saveTask(@PathVariable Long lessonId, @RequestBody TaskRequest taskRequest) {
        return taskService.saveTask(lessonId, taskRequest);
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public List<TaskResponse> findAllTasks() {
        return taskService.viewAllTasks();
    }

    @GetMapping("/all/{lessonId}")
    @PreAuthorize("isAuthenticated()")
    public List<TaskResponse> findAllTasks(@PathVariable Long lessonId) {
        return taskService.viewAllTasks(lessonId);
    }

    @GetMapping("/{taskId}")
    @PreAuthorize("isAuthenticated()")
    public TaskResponse findById(@PathVariable Long taskId) {
        return taskService.findTaskById(taskId);
    }

    @DeleteMapping("/{taskId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public TaskResponse deleteTaskById(@PathVariable Long taskId) {
        return taskService.deleteTaskById(taskId);
    }

    @PutMapping("/{taskId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public TaskResponse updateTask(@PathVariable Long taskId,
                                   @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(taskId, taskRequest);
    }
}
