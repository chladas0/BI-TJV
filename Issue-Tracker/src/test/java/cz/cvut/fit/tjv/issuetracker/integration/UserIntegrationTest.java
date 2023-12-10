package cz.cvut.fit.tjv.issuetracker.integration;

import cz.cvut.fit.tjv.issuetracker.controller.UserController;
import cz.cvut.fit.tjv.issuetracker.dto.UserCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.UserDTO;
import cz.cvut.fit.tjv.issuetracker.exception.EntityStateException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserIntegrationTest {

    @Autowired
    UserController userController;

    int idTestUser;

    @Test
    public void testCreateRead()
    {
        UserCreateDTO userCreateDTO = new UserCreateDTO("testUser", "default");

        UserDTO responseUser = userController.create(userCreateDTO);

        List<UserDTO> users = userController.getAll();

        Assertions.assertThat(users).anyMatch(user -> Objects.equals(user.getUsername(), "testUser"));

        userController.delete(responseUser.getId());

        idTestUser = responseUser.getId();
    }

    @Test
    public void errorHandlingNoEntityFoundExceptionThrown() {

        EntityStateException exception = assertThrows(EntityStateException.class, () -> {
            userController.getById(idTestUser);
        });

        assertEquals(exception.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
