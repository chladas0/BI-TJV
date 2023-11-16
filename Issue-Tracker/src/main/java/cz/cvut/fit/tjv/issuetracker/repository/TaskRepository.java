package cz.cvut.fit.tjv.issuetracker.repository;

import cz.cvut.fit.tjv.issuetracker.entity.Task;
import cz.cvut.fit.tjv.issuetracker.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM User u JOIN u.projects p JOIN p.tasks t WHERE u.id = :userId AND t.status <> 2")
    List<Task> findAllUnfinishedTasks(@Param("userId") int userId);
}
