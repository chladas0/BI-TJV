package cz.cvut.fit.tjv.issuetracker.controller;

import cz.cvut.fit.tjv.issuetracker.dto.*;
import cz.cvut.fit.tjv.issuetracker.entity.User;
import cz.cvut.fit.tjv.issuetracker.exception.EntityStateException;
import cz.cvut.fit.tjv.issuetracker.service.ProjectService;
import cz.cvut.fit.tjv.issuetracker.service.TaskService;
import cz.cvut.fit.tjv.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
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
    public UserDTO create(@RequestBody UserCreateDTO userCreateDTO)
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
    public UserDTO createProject(@PathVariable int id, @RequestBody ProjectCreateDTO projectCreateDTO){
        User user = crudService.findById(id).orElseThrow(
                () -> new EntityStateException("User not found"));

        ProjectDTO project = projectService.create(projectCreateDTO);
        user.getProjects().add(projectService.findById(project.getId()).orElseThrow());
        return crudService.findByIDasDTO(user.getId()).orElseThrow();
    }

    // todo vycistit
    @GetMapping("{id}/unfinishedTasks")
    public List<TaskDTO> getUnfinished(@PathVariable int id)
    {
        return taskService.findAllUnfinishedTasks(id);
    }
}
