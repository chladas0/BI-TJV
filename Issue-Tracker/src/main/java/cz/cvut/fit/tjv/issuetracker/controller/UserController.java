package cz.cvut.fit.tjv.issuetracker.controller;

import cz.cvut.fit.tjv.issuetracker.Entity.User;
import cz.cvut.fit.tjv.issuetracker.Exception.EntityStateException;
import cz.cvut.fit.tjv.issuetracker.dto.ProjectCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.ProjectDTO;
import cz.cvut.fit.tjv.issuetracker.dto.UserCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.UserDTO;
import cz.cvut.fit.tjv.issuetracker.service.ProjectService;
import cz.cvut.fit.tjv.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController extends CrudController<Integer, User, UserDTO, UserCreateDTO>
{
    private final ProjectService projectService;

    @Autowired
    public UserController(UserService userService, ProjectService projectService) {
        super(userService);
        this.projectService = projectService;
    }

    @GetMapping("/{id}/projects")
    List<ProjectDTO> projectsById(@PathVariable int id){
        User optAuthor = crudService.findById(id).orElseThrow(
                () -> new EntityStateException("Project not found"));

        return optAuthor.getProjects().stream().map(projectService::toDTO).collect(Collectors.toList());
    }

    @PostMapping("/{id}/projects")
    UserDTO createProject(@PathVariable int id, @RequestBody ProjectCreateDTO projectCreateDTO){
        User user = crudService.findById(id).orElseThrow(
                () -> new EntityStateException("User not found"));

        ProjectDTO project = projectService.create(projectCreateDTO);
        user.getProjects().add(projectService.findById(project.getId()).orElseThrow());
        return crudService.findByIDasDTO(user.getId()).orElseThrow();
    }
}
