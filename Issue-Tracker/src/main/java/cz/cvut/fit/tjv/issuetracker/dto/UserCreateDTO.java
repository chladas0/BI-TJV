package cz.cvut.fit.tjv.issuetracker.dto;


import lombok.Getter;

@Getter
public class UserCreateDTO{

    private final String username;

    private final String password;

    public UserCreateDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
