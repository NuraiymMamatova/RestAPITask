package com.peaksoft.project_on_restapi.service.impl;

import com.peaksoft.project_on_restapi.converter.request.TaskRequestConverter;
import com.peaksoft.project_on_restapi.converter.response.TaskResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.TaskRequest;
import com.peaksoft.project_on_restapi.dto.response.TaskResponse;
import com.peaksoft.project_on_restapi.model.entity.Lesson;
import com.peaksoft.project_on_restapi.model.entity.Task;
import com.peaksoft.project_on_restapi.repository.LessonRepository;
import com.peaksoft.project_on_restapi.repository.TaskRepository;
import com.peaksoft.project_on_restapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final LessonRepository lessonRepository;

    private final TaskRequestConverter taskRequestConverter;

    private final TaskResponseConverter taskResponseConverter;

    @Override
    public TaskResponseConverter getAll(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        taskResponseConverter.setTaskResponseList(viewPagination(search(name, pageable)));
        return taskResponseConverter;
    }

    @Override
    public List<TaskResponse> viewPagination(List<Task> tasks) {
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task : tasks) {
            taskResponseList.add(taskResponseConverter.viewTask(task));
        }
        return taskResponseList;
    }

    @Override
    public List<Task> search(String name, Pageable pageable) {
        String taskName = name == null ? "" : name;
        return taskRepository.searchPagination(taskName.toUpperCase(), pageable);
    }

    @Override
    public TaskResponse saveTask(Long lessonId, TaskRequest taskRequest) {
        Task task = taskRequestConverter.saveTask(taskRequest);
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found!"));
        lesson.addTasks(task);
        task.setLesson(lesson);
        taskRepository.save(task);
        return taskResponseConverter.viewTask(task);
    }

    @Override
    public TaskResponse deleteTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!"));
        taskRepository.delete(task);
        return taskResponseConverter.viewTask(task);
    }

    @Override
    public TaskResponse updateTask(Long taskId, TaskRequest taskRequest) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!"));
        taskRequestConverter.update(task, taskRequest);
        return taskResponseConverter.viewTask(taskRepository.save(task));
    }

    @Override
    public TaskResponse findTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!"));
        return taskResponseConverter.viewTask(task);
    }

    @Override
    public List<TaskResponse> viewAllTasks() {
        return taskResponseConverter.viewAllTask(taskRepository.findAll());
    }

    @Override
    public List<TaskResponse> viewAllTasks(Long lessonId) {
        if (lessonId == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Lesson id cannot be null!");
        }
        return taskResponseConverter.viewAllTask(taskRepository.getAllTasksById(lessonId));
    }

}
