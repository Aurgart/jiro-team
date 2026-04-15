package java_jabki.jiro_team.model.reports;

import java.time.LocalDate;

public record ReportData(Long teamId, LocalDate dateFrom, LocalDate dateTo) {
}
