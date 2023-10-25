package cz.cvut.fit.tjv.issuetracker.repository;

import cz.cvut.fit.tjv.issuetracker.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
