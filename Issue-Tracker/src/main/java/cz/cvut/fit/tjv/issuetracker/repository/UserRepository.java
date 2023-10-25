package cz.cvut.fit.tjv.issuetracker.repository;

import cz.cvut.fit.tjv.issuetracker.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
