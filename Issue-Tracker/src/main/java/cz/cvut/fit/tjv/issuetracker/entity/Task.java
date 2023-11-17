package cz.cvut.fit.tjv.issuetracker.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String taskName;

    private String description;

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private Date dueDate;

    @Column(nullable = false)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Task(String taskName, String description, Date creationDate, Date dueDate, TaskStatus status) {
        this.taskName = taskName;
        this.description = description;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Task() {}
}