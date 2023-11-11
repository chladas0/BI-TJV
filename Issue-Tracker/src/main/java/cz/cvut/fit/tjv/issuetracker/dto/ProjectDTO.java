package cz.cvut.fit.tjv.issuetracker.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class ProjectDTO {

    private final int id;

    private final String name;

    private final List<Integer> contributorsIds;

    public ProjectDTO(int id, String name, List<Integer> contributorsIds) {
        this.id = id;
        this.name = name;
        this.contributorsIds = contributorsIds;
    }
}
