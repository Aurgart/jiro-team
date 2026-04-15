package java_jabki.jiro_team.service;

import java_jabki.jiro_team.exception.TeamException;
import java_jabki.jiro_team.model.*;
import java_jabki.jiro_team.model.teams.*;
import java_jabki.jiro_team.repositaries.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teams;
    private final ExternalUserService users;
    private final ExternalTaskService tasks;

    public TeamResponse addTeam(TeamData team){
        validateTeam(team);
        TeamResponse tmp = new TeamResponse(teams.insert(team));
        for (Long user : team.members()){
            validateUser(user);
            addUser(tmp.getId(),user);
        }
        tmp.setMembers(team.members());
        return tmp;
    }

    public TeamMember addUser(MemberInfo member){
        validateManager(member.userId());
        return teams.addUser(member.member().teamId(),member.member().userId());
    }
    private TeamMember addUser(Long teamId, Long userId){
        validateUser(userId);
        return teams.addUser(teamId,userId);
    }
    public void deleteUser( MemberInfo member){
        if (!validateManager(member.userId())) {
            throw new TeamException("Пользователь должен быть руководителем!");
        }
        teams.deleteUser(member.member().teamId(),member.member().userId());
    }

    public TeamResponse getById(Long teamId){
        TeamResponse tmp = new TeamResponse(teams.getById(teamId));
        List<Long> members = new ArrayList<>();
        for (TeamMember member :   teams.getByTeam(teamId)){
            members.add(member.getUserId());
        }
        tmp.setMembers(members);
        return tmp;
    }

    private void validateTeam(TeamData team){
        if (team.name().isBlank()) {
            throw new TeamException("Наименование не указано!");
        }
        validateUser(team.ownerId());
        if (!validateManager(team.ownerId())) {
            throw new TeamException("Пользователь должен быть руководителем!");
        }
    }
    public boolean validateManager(Long id){
        return users.checkUser(id);
    }
    public void validateUser(Long id){
        if (!users.checkUser(id)) {
            throw new TeamException("Пользователь должен работать!");
        }
    }

    public Team updateTeam(TeamUpdate team){
        validateTeam(team);
        return teams.update(team);
    }
    private void validateTeam(TeamUpdate team){
        if (team.name().isBlank()) {
            throw new TeamException("Наименование не указано!");
        }
        validateUser(team.ownerId());
        if (!validateManager(team.ownerId())) {
            throw new TeamException("Пользователь должен быть руководителем!");
        }
    }

    public List<UserTaskList> getTeamUsersTasks(Long teamId){
        List<UserTaskList> tmp = new ArrayList<>();
        for (TeamMember member :   teams.getByTeam(teamId)){
           tmp.addAll(tasks.getTaskListUser(member.getUserId()));
        }
        return tmp;
    }
}
