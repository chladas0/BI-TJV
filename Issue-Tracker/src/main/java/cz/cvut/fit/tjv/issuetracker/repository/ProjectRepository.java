package cz.cvut.fit.tjv.issuetracker.repository;

import cz.cvut.fit.tjv.issuetracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
