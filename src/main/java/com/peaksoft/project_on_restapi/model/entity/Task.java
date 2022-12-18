package com.peaksoft.project_on_restapi.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_text")
    private String taskText;

    private LocalDate deadline;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH, PERSIST}, fetch = FetchType.EAGER)
    @JsonBackReference
    private Lesson lesson;

    public Task(String taskName, String taskText, LocalDate deadline) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadline = deadline;
    }
}