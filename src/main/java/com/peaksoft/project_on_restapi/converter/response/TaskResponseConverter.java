package com.peaksoft.project_on_restapi.converter.response;

import com.peaksoft.project_on_restapi.dto.response.TaskResponse;
import com.peaksoft.project_on_restapi.model.entity.Task;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class TaskResponseConverter {

    private List<TaskResponse> taskResponseList;

    public TaskResponse viewTask(Task task) {
        if (task == null) {
            return null;
        }
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTaskName(task.getTaskName());
        taskResponse.setTaskText(task.getTaskText());
        taskResponse.setDeadline(task.getDeadline());
        return taskResponse;
    }

    public List<TaskResponse> viewAllTask(List<Task> tasks) {
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : tasks) {
            taskResponses.add(viewTask(task));
        }
        return taskResponses;
    }
}
