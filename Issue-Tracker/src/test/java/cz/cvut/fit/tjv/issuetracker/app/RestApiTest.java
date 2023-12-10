package cz.cvut.fit.tjv.issuetracker.app;

import cz.cvut.fit.tjv.issuetracker.controller.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RestApiTest {
    @Autowired
    UserController userController;

    @Test
    public void contextLoadsTests()
    {
        Assertions.assertNotEquals(userController, null);
    }
}
