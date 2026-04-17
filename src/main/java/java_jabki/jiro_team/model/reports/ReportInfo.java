package java_jabki.jiro_team.model.reports;

import java.util.List;

public record ReportInfo(
        Long taskCount,
        List<TaskStateCnt> taskState,
        List<TaskAssCnt> assigneeTaskCount,
        Double workDays
) {
}
