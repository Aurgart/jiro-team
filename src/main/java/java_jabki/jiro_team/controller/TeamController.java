package java_jabki.jiro_team.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java_jabki.jiro_team.model.MemberInfo;
import java_jabki.jiro_team.model.UserTaskList;
import java_jabki.jiro_team.model.teams.TeamData;
import java_jabki.jiro_team.model.teams.TeamResponse;
import java_jabki.jiro_team.model.teams.Team;
import java_jabki.jiro_team.model.teams.TeamMember;
import java_jabki.jiro_team.model.teams.TeamUpdate;
import java_jabki.jiro_team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teams")
@Tag(name = "Команды")
public class TeamController {
    private final TeamService teams;

    @PostMapping
    @Operation(summary = "Создать команду")
    public TeamResponse create(@RequestBody TeamData team) {
        return teams.addTeam(team);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Команду по ид")
    public TeamResponse getById(@PathVariable("id") Long id) {
        return teams.getById(id);
    }

    @PatchMapping("/{id}/update")
    @Operation(summary = "Обновить информацию по команде")
    public Team updateTeam(@PathVariable("id") Long id, @RequestBody TeamUpdate team) {
        return teams.updateTeam(id, team);
    }

    @PostMapping("/{id}/member")
    @Operation(summary = "Добавить сотрудника в команду")
    public TeamMember addMember(@PathVariable("id") Long id, @RequestParam(required = true) Long userId) {
        return teams.addUser(id, userId);
    }

    @DeleteMapping("/{id}/member/del")
    @Operation(summary = "Убрать сотрудника из команды")
    public void delMember(@PathVariable("id") Long id, @RequestParam(required = true) Long managerId, @RequestParam(required = true) Long userId) {
        teams.deleteUser(id,managerId,userId);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "Задачи команды по ид")
    public List<UserTaskList> getTaskListByTeamId(@PathVariable("id") Long id) {
        return teams.getTeamUsersTasks(id);
    }
}
