package cz.cvut.fit.tjv.issuetracker.controller;

import cz.cvut.fit.tjv.issuetracker.Entity.User;
import cz.cvut.fit.tjv.issuetracker.dto.ProjectDTO;
import cz.cvut.fit.tjv.issuetracker.dto.UserCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.UserDTO;
import cz.cvut.fit.tjv.issuetracker.service.ProjectService;
import cz.cvut.fit.tjv.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController
{
    private final UserService userService;
    private final ProjectService projectService;

    @Autowired
    public UserController(UserService userService, ProjectService projectService) {this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/user/all")
    List<UserDTO> all() {return userService.findAll();}

    // todo fix exception
    @GetMapping("/user/{id}")
    UserDTO byId(@PathVariable int id) throws Exception {
        return userService.findByIDasDTO(id).orElseThrow(() -> new Exception("HTTP status not found "));
    }

    @GetMapping("/user/{id}/projects")
    List<ProjectDTO> projectsById(@PathVariable int id) throws Exception {
        Optional<User> optAuthor = userService.findById(id);

        if(optAuthor.isEmpty())
            throw new Exception("Author not found");

        return optAuthor.get().getProjects().stream().map(projectService::toDTO).collect(Collectors.toList());
    }

    @PostMapping("/user")
    UserDTO save(@RequestBody UserCreateDTO author) throws Exception {
        return userService.create(author);
    }

    @PutMapping("/user/{id}")
    UserDTO save(@PathVariable int id, @RequestBody UserCreateDTO author) throws Exception {
        return userService.update(id, author);
    }
}
