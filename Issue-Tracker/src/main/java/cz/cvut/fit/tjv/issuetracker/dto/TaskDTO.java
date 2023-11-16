package cz.cvut.fit.tjv.issuetracker.dto;

import cz.cvut.fit.tjv.issuetracker.entity.TaskStatus;
import lombok.Getter;

import java.util.Date;

@Getter
public class TaskDTO {
        private final int id;

        private final String taskName;

        private final String description;

        private final Date creationDate;

        private final Date dueDate;

        private final TaskStatus status;
        public TaskDTO(int id, String taskName, String description, Date creationDate, Date dueDate, TaskStatus status)
        {
            this.id = id;
            this.taskName = taskName;
            this.description = description;
            this.creationDate = creationDate;
            this.dueDate = dueDate;
            this.status = status;
        }
}
