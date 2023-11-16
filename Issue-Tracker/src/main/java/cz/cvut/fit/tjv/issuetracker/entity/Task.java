package cz.cvut.fit.tjv.issuetracker.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    public Task(String taskName, String description, Date creationDate, Date dueDate, TaskStatus status) {
        this.taskName = taskName;
        this.description = description;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Task() {}
}