package java_jabki.jiro_team.service;


import java_jabki.jiro_team.model.reports.ReportData;
import java_jabki.jiro_team.model.reports.ReportInfo;
import java_jabki.jiro_team.model.reports.ReportRequest;
import java_jabki.jiro_team.model.teams.TeamMember;
import java_jabki.jiro_team.repositaries.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamReportService {
    private final TeamRepository teams;
    private final ExternalTaskService tasks;

    public ReportInfo getTeamReport(ReportData report){
        List<Long> members = new ArrayList<>();
        for (TeamMember member :   teams.getByTeam(report.teamId())){
            members.add(member.getUserId());
        }
        ReportRequest rep = new ReportRequest(members,report.dateFrom(),report.dateTo());
        return tasks.getReport(rep);
    }
}
