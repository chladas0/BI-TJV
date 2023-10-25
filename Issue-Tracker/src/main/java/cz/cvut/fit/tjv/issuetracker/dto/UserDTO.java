package cz.cvut.fit.tjv.issuetracker.dto;

import lombok.Getter;

@Getter
public class UserDTO
{
    private final int id;
    private final String username;

    public UserDTO(int id, String userName) {
        this.id = id;
        this.username = userName;
    }
}
