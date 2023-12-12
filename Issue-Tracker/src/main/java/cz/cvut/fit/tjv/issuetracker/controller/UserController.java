package cz.cvut.fit.tjv.issuetracker.controller;

import cz.cvut.fit.tjv.issuetracker.dto.*;
import cz.cvut.fit.tjv.issuetracker.entity.User;
import cz.cvut.fit.tjv.issuetracker.exception.EntityStateException;
import cz.cvut.fit.tjv.issuetracker.service.ProjectService;
import cz.cvut.fit.tjv.issuetracker.service.TaskService;
import cz.cvut.fit.tjv.issuetracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController extends CrudController<Integer, User, UserDTO, UserCreateDTO>
{
    private final ProjectService projectService;
    private final TaskService taskService;

    public UserController(UserService userService, ProjectService projectService, TaskService taskService) {
        super(userService);
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @PostMapping()
    public UserDTO create(@RequestBody @Valid UserCreateDTO userCreateDTO)
    {
        return super.create(userCreateDTO);
    }

    @GetMapping("/{id}/projects")
    public List<ProjectDTO> projectsById(@PathVariable int id){
        User optAuthor = crudService.findById(id).orElseThrow(
                () -> new EntityStateException("User not found"));

        return optAuthor.getProjects().stream().map(projectService::toDTO).collect(Collectors.toList());
    }

    @PostMapping("/{id}/projects")
    public ProjectDTO createProject(@PathVariable int id, @RequestBody @Valid ProjectCreateDTO projectCreateDTO){
        crudService.findById(id).orElseThrow(
                () -> new EntityStateException("User not found"));

        if(!projectCreateDTO.getContributorsIds().contains(id))
            projectCreateDTO.getContributorsIds().add(id);

        return projectService.create(projectCreateDTO);
    }

    @GetMapping("{id}/unfinishedTasks")
    public List<TaskDTO> getUnfinished(@PathVariable int id)
    {
        return taskService.findAllUnfinishedTasks(id);
    }
}
