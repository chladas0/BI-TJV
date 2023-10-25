package cz.cvut.fit.tjv.issuetracker.service;

import cz.cvut.fit.tjv.issuetracker.Entity.User;
import cz.cvut.fit.tjv.issuetracker.Entity.Project;
import cz.cvut.fit.tjv.issuetracker.dto.ProjectCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.ProjectDTO;
import cz.cvut.fit.tjv.issuetracker.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProjectService extends CrudService<Integer,Project, ProjectDTO, ProjectCreateDTO>{
    private final UserService userService;

    @Autowired
    public ProjectService(ProjectRepository ProjectRepository, UserService userService) {
        super(ProjectRepository);
        this.userService = userService;
    }

    @Override
    public ProjectDTO create(ProjectCreateDTO Project) throws Exception {
        List<User> users = userService.findByIDs(Project.getContributorsIds());

        if(users.size() != Project.getContributorsIds().size()) {
            throw new Exception("Some authors not found {fix me :)}");
        }

        return toDTO(repository.save(toEntity(Project)));
    }

    public ProjectDTO toDTO(Project Project)
    {
        return new ProjectDTO(Project.getId(), Project.getName(),
                Project.getContributors().stream().map(User::getId).collect(Collectors.toList()));
    }

    @Override
    protected Project toEntity(ProjectCreateDTO projectCreateDTO) {
        return new Project(projectCreateDTO.getName(), userService.findByIDs(projectCreateDTO.getContributorsIds()), projectCreateDTO.getDescription());
    }

    protected Project updateEntity(Project existingProject, ProjectCreateDTO e) throws Exception {

        List<User> users = userService.findByIDs(e.getContributorsIds());

        if(users.size() != existingProject.getContributors().size()) {
            throw new Exception("Some authors not found {fix me :)}");
        }

        existingProject.setName(e.getName());
        existingProject.setContributors(userService.findByIDs(e.getContributorsIds()));
        existingProject.setDescription(e.getDescription());

        return existingProject;
    }
}
