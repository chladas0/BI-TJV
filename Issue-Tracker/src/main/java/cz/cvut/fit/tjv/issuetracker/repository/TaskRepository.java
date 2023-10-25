package cz.cvut.fit.tjv.issuetracker.repository;

import cz.cvut.fit.tjv.issuetracker.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
