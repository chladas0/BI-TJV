package cz.cvut.fit.tjv.issuetracker.controller;

import cz.cvut.fit.tjv.issuetracker.dto.*;
import cz.cvut.fit.tjv.issuetracker.entity.Project;
import cz.cvut.fit.tjv.issuetracker.service.ProjectService;
import cz.cvut.fit.tjv.issuetracker.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
public class ProjectController extends CrudController<Integer, Project, ProjectDTO, ProjectCreateDTO>
{
    private final TaskService taskService;

    public ProjectController(ProjectService projectService, TaskService taskService) {
        super(projectService);
        this.taskService = taskService;
    }

    @PostMapping()
    public ProjectDTO create(@RequestBody ProjectCreateDTO projectCreateDTO)
    {
        return super.create(projectCreateDTO);
    }

    @Transactional
    @PostMapping("/{id}/tasks")
    public TaskDTO createTask(@PathVariable int id, @RequestBody TaskCreateDTO taskCreateDTO){
        Project project = crudService.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserNotFound"));

            TaskDTO task = taskService.create(taskCreateDTO);
            taskService.findById(task.getId()).get().setProject(project); // todo fix
            project.getTasks().add(taskService.findById(task.getId()).orElseThrow());
            return taskService.findByIDasDTO(task.getId()).orElseThrow();
    }


    @GetMapping("/{id}/tasks")
    public List<TaskDTO> getTasks(@PathVariable int id)
    {
        Project project = crudService.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserNotFound"));

        return project.getTasks().stream().map(taskService::toDTO).collect(Collectors.toList());
    }
}
