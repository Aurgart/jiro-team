package java_jabki.jiro_team.model.teams;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TeamMember {
    private Long teamId;
    private Long userId;
}
