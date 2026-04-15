package java_jabki.jiro_team.repositaries.Mapper;

import java_jabki.jiro_team.model.teams.Team;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TeamMapper implements RowMapper<Team> {

    @Override
    public Team mapRow(ResultSet rs, int rownum) throws SQLException{
        return Team.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .ownerId(rs.getLong("owner_id"))
                .build();
    }
}
