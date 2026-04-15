package java_jabki.jiro_team.repositaries.Mapper;

import java_jabki.jiro_team.model.teams.TeamMember;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TeamMemberMapper implements RowMapper<TeamMember> {
    @Override
    public TeamMember mapRow(ResultSet rs, int rownum) throws SQLException {
        return TeamMember.builder()
                .teamId(rs.getLong("team_id"))
                .userId(rs.getLong("user_id"))
                .build();
    }
}
