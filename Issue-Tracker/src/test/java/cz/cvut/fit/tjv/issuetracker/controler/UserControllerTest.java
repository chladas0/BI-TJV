package cz.cvut.fit.tjv.issuetracker.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fit.tjv.issuetracker.config.SecurityConfig;
import cz.cvut.fit.tjv.issuetracker.controller.UserController;
import cz.cvut.fit.tjv.issuetracker.dto.UserCreateDTO;
import cz.cvut.fit.tjv.issuetracker.dto.UserDTO;
import cz.cvut.fit.tjv.issuetracker.exception.IllegalDataException;
import cz.cvut.fit.tjv.issuetracker.service.ProjectService;
import cz.cvut.fit.tjv.issuetracker.service.TaskService;
import cz.cvut.fit.tjv.issuetracker.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
@Import(SecurityConfig.class)
public class UserControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    ProjectService projectService;

    @MockBean
    TaskService taskService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testFindAll() throws Exception {
        UserDTO user1 = new UserDTO(1, "Filip Glazar");
        UserDTO user2 = new UserDTO(2, "Frodo Pytlík");

        List<UserDTO> users = List.of(user1, user2);

        Mockito.when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].username", Matchers.is("Filip Glazar")))
                .andExpect(jsonPath("$[1].username", Matchers.is("Frodo Pytlík")));
    }

    @Test
    public void testCreateExistingUser() throws Exception {

        UserCreateDTO existingUser = new UserCreateDTO("Filip Glazar", "okurek");

        Mockito.when(userService.create(Mockito.any(UserCreateDTO.class)))
                .thenThrow(new IllegalDataException("Username is not unique"));

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(existingUser)))
                .andExpect(status().isBadRequest());
    }
}