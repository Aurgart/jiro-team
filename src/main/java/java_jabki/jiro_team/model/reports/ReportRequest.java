package java_jabki.jiro_team.model.reports;

import java.time.LocalDate;
import java.util.List;

public record ReportRequest(List<Long> userIds, LocalDate dateFrom, LocalDate dateTo) {
}
