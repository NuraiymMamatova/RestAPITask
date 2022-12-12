package com.peaksoft.project_on_restapi.api;

import com.peaksoft.project_on_restapi.dto.request.TaskRequest;
import com.peaksoft.project_on_restapi.dto.response.TaskResponse;
import com.peaksoft.project_on_restapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task_api")
public class TaskApi {

    private final TaskService taskService;

    @PostMapping("/save/{lessonId}")
    public TaskResponse saveTask(@PathVariable Long lessonId, @RequestBody TaskRequest taskRequest) {
        return taskService.saveTask(lessonId, taskRequest);
    }

    @GetMapping("/all")
    public List<TaskResponse> findAllTasks() {
        return taskService.viewAllTasks();
    }

    @GetMapping("/{taskId}")
    public TaskResponse findById(@PathVariable Long taskId) {
        return taskService.findTaskById(taskId);
    }

    @DeleteMapping("/{taskId}")
    public TaskResponse deleteTaskById(@PathVariable Long taskId) {
        return taskService.deleteTaskById(taskId);
    }

    @PutMapping("/{taskId}")
    public TaskResponse updateTask(@PathVariable Long taskId,
                                   @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(taskId, taskRequest);
    }
}
