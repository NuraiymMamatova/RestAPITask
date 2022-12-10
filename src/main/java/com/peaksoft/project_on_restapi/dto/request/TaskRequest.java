package com.peaksoft.project_on_restapi.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TaskRequest {

    private String taskName;

    private String taskText;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

}
