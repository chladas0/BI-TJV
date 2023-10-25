package cz.cvut.fit.tjv.issuetracker.service;

import cz.cvut.fit.tjv.issuetracker.Entity.User;
import cz.cvut.fit.tjv.issuetracker.Entity.Project;
import cz.cvut.fit.tjv.issuetracker.dto.ProjectCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.ProjectDTO;
import cz.cvut.fit.tjv.issuetracker.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProjectService {

    private final ProjectRepository ProjectRepository;
    private final UserService userService;

    @Autowired
    public ProjectService(ProjectRepository ProjectRepository, UserService userService) {
        this.ProjectRepository = ProjectRepository;
        this.userService = userService;
    }

    // Finds -----------------------------------------------------------------------------------------------------------
    public Optional<Project> findById(int id) {
        return ProjectRepository.findById(id);
    }

    public Optional<ProjectDTO> findByIDasDTO(int id)
    {
        return toDTO(ProjectRepository.findById(id));
    }

    public List<ProjectDTO> findAll()
    {
        return ProjectRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Project> findByIDs(List<Integer> ids)
    {
        return ProjectRepository.findAllById(ids);
    }
    // -----------------------------------------------------------------------------------------------------------------

    public ProjectDTO create(ProjectCreateDTO Project) throws Exception {
        List<User> users = userService.findByIDs(Project.getContributorsIds());

        // todo own exception add user that was not found
        if(users.size() != Project.getContributorsIds().size()) {
            throw new Exception("Some authors not found {fix me :)}");
        }
        return toDTO(ProjectRepository.save(new Project(Project.getName(), users, Project.getDescription())));
    }

    // method is public and called from outside, transaction, commit, saved when its managed
    @Transactional
    public ProjectDTO update(int id, ProjectCreateDTO ProjectCreateDto) throws Exception {
        Optional<Project> optProject = findById(id);

        // todo exception class catch in controllers
        if(optProject.isEmpty())
            throw new Exception("No such Project {fix me :)}");

        List<User> users = userService.findByIDs(ProjectCreateDto.getContributorsIds());

        // todo own exception add user that was not found
        if(users.size() != ProjectCreateDto.getContributorsIds().size()) {
            throw new Exception("Some authors not found {fix me :)}");
        }

        Project Project = optProject.get();
        Project.setName(ProjectCreateDto.getName());
        Project.setContributors(userService.findByIDs(ProjectCreateDto.getContributorsIds()));

        return toDTO(Project);
    }


    // Helpers ---------------------------------------------------------------------------------------------------------
    public ProjectDTO toDTO(Project Project)
    {
        return new ProjectDTO(Project.getId(), Project.getName(),
                Project.getContributors().stream().map(User::getId).collect(Collectors.toList()));
    }

    private Optional<ProjectDTO> toDTO(Optional<Project> Project)
    {
        return Project.map(this::toDTO);
    }
    // -----------------------------------------------------------------------------------------------------------------

}
