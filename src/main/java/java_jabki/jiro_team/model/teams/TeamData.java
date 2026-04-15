package java_jabki.jiro_team.model.teams;

import java.util.List;

public record TeamData(
                       String name,
                       Long ownerId,
                       List<Long> members
         ) {
}
