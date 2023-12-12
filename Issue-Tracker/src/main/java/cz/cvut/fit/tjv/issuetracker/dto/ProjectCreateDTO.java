package cz.cvut.fit.tjv.issuetracker.dto;


import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class ProjectCreateDTO {
    private final String name;
    
    private final List<Integer> contributorsIds;

    private final String description;

    public ProjectCreateDTO(@NonNull String name, @NonNull List<Integer> contributorsIds, @NonNull String description)
    {
        this.name = name;
        this.contributorsIds = contributorsIds;
        this.description = description;
    }
}
