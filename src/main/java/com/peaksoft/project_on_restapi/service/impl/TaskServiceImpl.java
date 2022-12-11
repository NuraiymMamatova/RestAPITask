package com.peaksoft.project_on_restapi.service.impl;

import com.peaksoft.project_on_restapi.converter.request.TaskRequestConverter;
import com.peaksoft.project_on_restapi.converter.response.TaskResponseConverter;
import com.peaksoft.project_on_restapi.dto.request.TaskRequest;
import com.peaksoft.project_on_restapi.dto.response.TaskResponse;
import com.peaksoft.project_on_restapi.model.entity.Lesson;
import com.peaksoft.project_on_restapi.model.entity.Student;
import com.peaksoft.project_on_restapi.model.entity.Task;
import com.peaksoft.project_on_restapi.repository.LessonRepository;
import com.peaksoft.project_on_restapi.repository.TaskRepository;
import com.peaksoft.project_on_restapi.service.StudentService;
import com.peaksoft.project_on_restapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final LessonRepository lessonRepository;

    private final TaskRequestConverter taskRequestConverter;

    private final TaskResponseConverter taskResponseConverter;

    @Override
    public TaskResponse saveTask(TaskRequest taskRequest) {
        Task task = taskRequestConverter.saveTask(taskRequest);
        taskRepository.save(task);
        return taskResponseConverter.viewTask(task);
    }

    @Override
    public TaskResponse saveTask(Long lessonId, TaskRequest taskRequest) {
        Task task = taskRequestConverter.saveTask(taskRequest);
        Lesson lesson = lessonRepository.findById(lessonId).get();
        lesson.addTasks(task);
        task.setLesson(lesson);
        taskRepository.save(task);
        return taskResponseConverter.viewTask(task);
    }

    @Override
    public TaskResponse deleteTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).get();
        taskRepository.delete(task);
        return taskResponseConverter.viewTask(task);
    }

    @Override
    public TaskResponse updateTask(Long taskId, TaskRequest taskRequest) {
        Task task = taskRepository.findById(taskId).get();
        taskRequestConverter.update(task, taskRequest);
        return taskResponseConverter.viewTask(taskRepository.save(task));
    }

    @Override
    public TaskResponse findTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).get();
        return taskResponseConverter.viewTask(task);
    }

    @Override
    public List<TaskResponse> viewAllTasks() {
        return taskResponseConverter.viewAllTask(taskRepository.findAll());
    }

    @Override
    public List<Task> getAllTasks(Long id) {
        return null;
    }
}
