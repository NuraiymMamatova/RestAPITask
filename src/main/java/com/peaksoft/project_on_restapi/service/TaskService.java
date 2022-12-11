package com.peaksoft.project_on_restapi.service;

import com.peaksoft.project_on_restapi.dto.request.TaskRequest;
import com.peaksoft.project_on_restapi.dto.response.TaskResponse;
import com.peaksoft.project_on_restapi.model.entity.Task;

import java.util.List;

public interface TaskService {

    TaskResponse saveTask(TaskRequest taskRequest);

    TaskResponse saveTask(Long lessonId, TaskRequest taskRequest);

    TaskResponse deleteTaskById(Long taskId);

    TaskResponse updateTask(Long taskId, TaskRequest taskRequest);

    TaskResponse findTaskById(Long taskId);

    List<TaskResponse> viewAllTasks();

    List<Task> getAllTasks(Long id);
}
