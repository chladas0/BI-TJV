package cz.cvut.fit.tjv.issuetracker.service;

import cz.cvut.fit.tjv.issuetracker.dto.UserDTO;
import cz.cvut.fit.tjv.issuetracker.entity.User;
import cz.cvut.fit.tjv.issuetracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void testFindAllUsers()
    {
        UserDTO user1 = new UserDTO(1, "Filip Glazar");
        UserDTO user2 = new UserDTO(2, "Pat");
        UserDTO user3 = new UserDTO(3, "Mat");

        List<UserDTO> list = new ArrayList<>(List.of(user1, user2, user3));

        when(userService.findAll()).thenReturn(list);

        Collection<User> userList = userService.findAllAsEntity().stream().toList();
        assertEquals(3, userList.size());
        verify(userRepository, times(1)).findAll();
    }
}
