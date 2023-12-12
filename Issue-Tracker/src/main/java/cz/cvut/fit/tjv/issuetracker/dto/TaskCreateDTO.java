package cz.cvut.fit.tjv.issuetracker.dto;

import cz.cvut.fit.tjv.issuetracker.entity.TaskStatus;
import lombok.Getter;
import lombok.NonNull;

import java.util.Date;

@Getter
public class TaskCreateDTO {
        private final String taskName;

        private final String description;

        private final Date creationDate;

        private final Date dueDate;

        private final TaskStatus status;
        public TaskCreateDTO(@NonNull String taskName, @NonNull String description, @NonNull Date creationDate,
                             @NonNull Date dueDate, @NonNull TaskStatus status) {
            this.taskName = taskName;
            this.description = description;
            this.creationDate = creationDate;
            this.dueDate = dueDate;
            this.status = status;
        }
}
