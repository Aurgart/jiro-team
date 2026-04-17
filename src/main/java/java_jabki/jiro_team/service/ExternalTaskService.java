package java_jabki.jiro_team.service;


import java_jabki.jiro_team.model.UserTaskList;
import java_jabki.jiro_team.model.reports.ReportInfo;
import java_jabki.jiro_team.model.reports.ReportRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ExternalTaskService {

    private final RestClient restClient;
    @Value("${app.url.task}")
    private String url;

    public ExternalTaskService() {
        if (url == null) {
            this.restClient = RestClient.builder().baseUrl("http://localhost:8086/api/v3").build();
        } else {
            this.restClient = RestClient.builder().baseUrl(url).build();
        }
    }

    public List<UserTaskList> getTaskListUser(long userId) {
        return restClient.get().uri("/tasks/user_task_list/{id}", userId).retrieve().body(new ParameterizedTypeReference<>() {});
    }

    public ReportInfo getReport(ReportRequest rep){
        return restClient.patch().uri("/tasks/report").body(rep).retrieve().body(new ParameterizedTypeReference<>() {});
    }
}
