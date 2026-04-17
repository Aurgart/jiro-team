package java_jabki.jiro_team.repositaries;

import java_jabki.jiro_team.model.teams.Team;
import java_jabki.jiro_team.model.teams.TeamData;
import java_jabki.jiro_team.model.teams.TeamMember;
import java_jabki.jiro_team.model.teams.TeamUpdate;
import java_jabki.jiro_team.repositaries.Mapper.TeamMapper;
import java_jabki.jiro_team.repositaries.Mapper.TeamMemberMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TeamRepository {
    private static final String INSERT = """
            INSERT INTO jiro_team.team(name, owner_id)
            VALUES (:name, :owner_id)
            RETURNING *;
            """;
    private static final String UPDATE = """
            UPDATE  jiro_team.team
            SET  name = :name,
                owner_id = :owner_id
            WHERE id = :id
            RETURNING *;
            """;
    private static final String GET_BY_ID = """
            SELECT *
            FROM jiro_team.team
            WHERE id = :id
            """;
    private static final String ADD_USER = """
            INSERT INTO jiro_team.team_members(team_id, user_id)
            VALUES (:team_id, :user_id)
            RETURNING *;
            """;
    private static final String GET_USER_TEAM = """
            SELECT *
            FROM jiro_team.team_members
            WHERE team_id = :team_id
            """;
    private static final String DELETE_USER_TEAM = """
            DELETE FROM jiro_team.team_members
            WHERE user_id = :user_id
              AND team_id = :team_id
            """;

    private final TeamMapper teamMapper;
    private final TeamMemberMapper memberMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Team insert(TeamData team) {
        return jdbcTemplate.queryForObject(INSERT, teamParamForSql(team), teamMapper);
    }

    public Team update(Long id, TeamUpdate team) {
        return jdbcTemplate.queryForObject(UPDATE, teamUpdParamForSql(id, team), teamMapper);
    }

    public Team getById(Long id) {
        return jdbcTemplate.queryForObject(GET_BY_ID, new MapSqlParameterSource("id", id), teamMapper);
    }

    public TeamMember addUser(Long teamId, Long userId) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("team_id", teamId);
        params.addValue("user_id", userId);
        return jdbcTemplate.queryForObject(ADD_USER, params, memberMapper);
    }

    public void deleteUser(Long teamId, Long userId) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("team_id", teamId);
        params.addValue("user_id", userId);
        jdbcTemplate.update(DELETE_USER_TEAM, params);
    }

    public List<TeamMember> getByTeam(Long id) {
        return jdbcTemplate.query(GET_USER_TEAM, new MapSqlParameterSource("team_id", id), memberMapper);
    }


    private MapSqlParameterSource teamParamForSql(TeamData team) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", team.name());
        params.addValue("owner_id", team.ownerId());
        return params;
    }

    private MapSqlParameterSource teamUpdParamForSql(Long id, TeamUpdate team) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", team.name());
        params.addValue("name", team.name());
        params.addValue("owner_id", team.ownerId());
        return params;
    }


}
