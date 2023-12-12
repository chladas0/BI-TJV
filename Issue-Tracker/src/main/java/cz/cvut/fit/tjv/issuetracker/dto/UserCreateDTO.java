package cz.cvut.fit.tjv.issuetracker.dto;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class UserCreateDTO{

    private final String username;

    private final String password;

    public UserCreateDTO(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }
}
