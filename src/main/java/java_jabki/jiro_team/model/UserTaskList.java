package java_jabki.jiro_team.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserTaskList {
    private String title;
    private String description;
    private LocalDate deadLine;
}
