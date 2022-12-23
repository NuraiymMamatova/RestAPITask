package com.peaksoft.project_on_restapi.service;

import com.peaksoft.project_on_restapi.converter.response.TaskResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.TaskRequest;
import com.peaksoft.project_on_restapi.dto.response.TaskResponse;
import com.peaksoft.project_on_restapi.model.entity.Task;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    TaskResponse saveTask(Long lessonId, TaskRequest taskRequest);

    TaskResponse deleteTaskById(Long taskId);

    TaskResponse updateTask(Long taskId, TaskRequest taskRequest);

    TaskResponse findTaskById(Long taskId);

    List<TaskResponse> viewAllTasks();

    List<TaskResponse> viewAllTasks(Long lessonId);

    TaskResponseConverter getAll(String taskName, int page, int size);

    List<TaskResponse> viewPagination(List<Task> tasks);

    List<Task> search(String name, Pageable pageable);
}
