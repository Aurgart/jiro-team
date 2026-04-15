package java_jabki.jiro_team.model;

import lombok.Data;

@Data
public class ApiError {
    final boolean result;
    final String description;
}
