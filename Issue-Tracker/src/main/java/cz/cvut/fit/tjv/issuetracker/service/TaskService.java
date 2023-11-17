package cz.cvut.fit.tjv.issuetracker.service;

import cz.cvut.fit.tjv.issuetracker.entity.Task;
import cz.cvut.fit.tjv.issuetracker.dto.TaskCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.TaskDTO;
import cz.cvut.fit.tjv.issuetracker.exception.EntityStateException;
import cz.cvut.fit.tjv.issuetracker.repository.TaskRepository;
import cz.cvut.fit.tjv.issuetracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService extends CrudService<Integer, Task, TaskDTO, TaskCreateDTO>{

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    public TaskService(TaskRepository taskRepository, TaskRepository taskRepository1, UserRepository userRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository1;
        this.userRepository = userRepository;
    }

    @Override
    protected Task updateEntity(Task existingEntity, TaskCreateDTO e){
        existingEntity.setTaskName(e.getTaskName());
        existingEntity.setDescription(e.getDescription());
        existingEntity.setCreationDate(e.getCreationDate());
        existingEntity.setDueDate(e.getDueDate());
        existingEntity.setStatus(e.getStatus());
        return existingEntity;
    }

    public TaskDTO toDTO(Task t)
    {
        return new TaskDTO(t.getId(), t.getTaskName(), t.getDescription(), t.getCreationDate(), t.getDueDate(), t.getStatus());
    }

    @Override
    protected Task toEntity(TaskCreateDTO t) {
        return new Task(t.getTaskName(), t.getDescription(), t.getCreationDate(), t.getDueDate(), t.getStatus());
    }

    public List<TaskDTO> findAllUnfinishedTasks(int userId)
    {
        if(userRepository.findById(userId).isEmpty())
            throw new EntityStateException("User not found");

        return taskRepository.findAllUnfinishedTasks(userId).stream().map(this::toDTO).collect(Collectors.toList());
    }
}
