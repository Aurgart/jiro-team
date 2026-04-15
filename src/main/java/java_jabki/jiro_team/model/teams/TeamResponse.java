package java_jabki.jiro_team.model.teams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class TeamResponse {
    private Long id;
    private String name;
    private Long ownerId;
    private List<Long> members;

    public TeamResponse(Team team){
        this.id = team.getId();
        this.name = team.getName();
        this.ownerId = team.getOwnerId();
    }
}
