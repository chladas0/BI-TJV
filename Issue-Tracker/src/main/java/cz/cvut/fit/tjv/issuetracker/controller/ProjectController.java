package cz.cvut.fit.tjv.issuetracker.controller;

import cz.cvut.fit.tjv.issuetracker.Entity.Project;
import cz.cvut.fit.tjv.issuetracker.Entity.User;
import cz.cvut.fit.tjv.issuetracker.dto.ProjectCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.ProjectDTO;
import cz.cvut.fit.tjv.issuetracker.dto.TaskCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.TaskDTO;
import cz.cvut.fit.tjv.issuetracker.service.ProjectService;
import cz.cvut.fit.tjv.issuetracker.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
public class ProjectController extends CrudController<Integer, Project, ProjectDTO, ProjectCreateDTO>
{
    private final TaskService taskService;

    @Autowired
    public ProjectController(ProjectService projectService, TaskService taskService) {
        super(projectService);
        this.taskService = taskService;
    }

    @Transactional
    @PostMapping("/{id}/tasks")
    public TaskDTO createTask(@PathVariable int id, @RequestBody TaskCreateDTO taskCreateDTO) throws Exception {
        Project project = crudService.findById(id).orElseThrow();
        TaskDTO task = taskService.create(taskCreateDTO);

        project.getTasks().add(taskService.findById(task.getId()).orElseThrow());
        return taskService.findByIDasDTO(task.getId()).orElseThrow();
    }


    @GetMapping("/{id}/tasks")
    List<TaskDTO> getTasks(@PathVariable int id)
    {
        Project project = crudService.findById(id).orElseThrow();
        return project.getTasks().stream().map(taskService::toDTO).collect(Collectors.toList());
    }
}
