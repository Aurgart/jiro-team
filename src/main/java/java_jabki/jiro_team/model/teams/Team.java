package java_jabki.jiro_team.model.teams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Team {
    private Long id;
    private String name;
    private Long ownerId;
}
