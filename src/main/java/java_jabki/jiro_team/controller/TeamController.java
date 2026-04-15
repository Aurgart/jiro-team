package java_jabki.jiro_team.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java_jabki.jiro_team.model.*;
import java_jabki.jiro_team.model.teams.*;
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
    public TeamResponse create(@RequestBody TeamData team){ return teams.addTeam(team); }

    @GetMapping("/{id}")
    @Operation(summary = "Команду по ид")
    public TeamResponse getById(@PathVariable("id") Long id){ return teams.getById(id); }

    @PatchMapping
    @Operation(summary = "Обновить информацию по команде")
    public Team updateTeam(@RequestBody TeamUpdate team) {return teams.updateTeam(team);}

    @PostMapping("/member")
    @Operation(summary = "Добавить сотрудника в команду")
    public TeamMember addMember(@RequestBody MemberInfo member){ return teams.addUser(member);}

    @DeleteMapping("/member/del")
    @Operation(summary = "Убрать сотрудника из команды")
    public void delMember(@RequestBody MemberInfo member){ teams.deleteUser(member);}

    @GetMapping("/task_list/{id}")
    @Operation(summary = "Задачи команды по ид")
    public List<UserTaskList> getTaskListByTeamId(@PathVariable("id") Long id){ return teams.getTeamUsersTasks(id); }
}
