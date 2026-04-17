package java_jabki.jiro_team.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java_jabki.jiro_team.model.reports.ReportData;
import java_jabki.jiro_team.model.reports.ReportInfo;
import java_jabki.jiro_team.model.teams.Team;
import java_jabki.jiro_team.model.teams.TeamUpdate;
import java_jabki.jiro_team.service.TeamReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reports")
@Tag(name = "Отчеты")
public class ReportController {
    private final TeamReportService reports;

    @PatchMapping
    @Operation(summary = "Получить отчет по команде")
    public ReportInfo updateTeam(@RequestBody ReportData rep) {
        return reports.getTeamReport(rep);
    }
}
