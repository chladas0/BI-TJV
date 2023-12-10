package cz.cvut.fit.tjv.issuetracker.repository;

import cz.cvut.fit.tjv.issuetracker.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.stream.Collectors;


@ExtendWith(SpringExtension.class)
@DataJpaTest
//Used when modifying state like embeddedDatabase
@DirtiesContext
//H2 database instead of postgres
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

public class RepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testCreateReadDelete()
    {
        User user = new User("Pat", "Mat");

        userRepository.save(user);

        List<User> users = userRepository.findAll();

        List<String> usernames = users.stream().map(User::getUsername).collect(Collectors.toList());
        Assertions.assertEquals(List.of("Pat"), usernames);

        userRepository.deleteAll();
        Assertions.assertTrue(userRepository.findAll().isEmpty());
    }
}
