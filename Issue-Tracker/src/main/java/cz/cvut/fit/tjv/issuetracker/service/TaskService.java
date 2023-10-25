package cz.cvut.fit.tjv.issuetracker.service;

import cz.cvut.fit.tjv.issuetracker.Entity.Task;
import cz.cvut.fit.tjv.issuetracker.dto.TaskCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.TaskDTO;
import cz.cvut.fit.tjv.issuetracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    TaskRepository taskRepository;

    public TaskDTO create(TaskCreateDTO taskCreateDTO) throws Exception {
        return toDTO(taskRepository.save( new Task(
                taskCreateDTO.getTaskName(),
                taskCreateDTO.getDescription(),
                taskCreateDTO.getCreationDate(),
                taskCreateDTO.getDueDate(),
                taskCreateDTO.getStatus())));
    }

    public TaskDTO toDTO(Task t)
    {
        return new TaskDTO(t.getId(), t.getTaskName(), t.getDescription(), t.getCreationDate(), t.getDueDate(), t.getStatus());
    }
}
