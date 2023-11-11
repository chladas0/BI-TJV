package cz.cvut.fit.tjv.issuetracker.controller;


import cz.cvut.fit.tjv.issuetracker.Entity.Task;
import cz.cvut.fit.tjv.issuetracker.dto.TaskCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.TaskDTO;
import cz.cvut.fit.tjv.issuetracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController extends CrudController<Integer, Task, TaskDTO, TaskCreateDTO>{

    @Autowired
    public TaskController(TaskService taskService) {
        super(taskService);
    }
}
