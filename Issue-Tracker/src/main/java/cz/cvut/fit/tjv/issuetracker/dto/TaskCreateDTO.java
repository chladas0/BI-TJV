package cz.cvut.fit.tjv.issuetracker.dto;

import cz.cvut.fit.tjv.issuetracker.Entity.TaskStatus;
import lombok.Getter;

import java.util.Date;

@Getter
public class TaskCreateDTO {
        private final String taskName;

        private final String description;

        private final Date creationDate;

        private final Date dueDate;

        private final TaskStatus status;
        public TaskCreateDTO(String taskName, String description, Date creationDate, Date dueDate, TaskStatus status) {
            this.taskName = taskName;
            this.description = description;
            this.creationDate = creationDate;
            this.dueDate = dueDate;
            this.status = status;
        }
}
