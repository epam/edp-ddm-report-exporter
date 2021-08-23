package com.epam.digital.data.platform.reportexporter.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.epam.digital.data.platform.reportexporter.client.DashboardClient;
import com.epam.digital.data.platform.reportexporter.model.Dashboard;
import com.epam.digital.data.platform.reportexporter.model.Page;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

  ReportService instance;

  @Mock
  DashboardClient dashboardClient;

  @Mock
  DashboardArchiver archiver;

  @Mock
  QueryHelper queryHelper;

  @BeforeEach
  void setup() {
    instance = new ReportService(dashboardClient, queryHelper, archiver);
  }

  @Test
  void shouldReturnDashboards() {
    when(dashboardClient.getDashboards()).thenReturn(mockPageResponse(HttpStatus.OK));

    instance.getDashboards();
  }

  @Test
  void shouldThrowExceptionWhenNoDashboardFound() {
    when(dashboardClient.getDashboards()).thenReturn(mockPageResponse(HttpStatus.NOT_FOUND));

    assertThatThrownBy(() -> instance.getDashboards())
        .isInstanceOf(ResponseStatusException.class)
        .hasMessageContaining("404 NOT_FOUND");
  }

  private ResponseEntity<Page<Dashboard>> mockPageResponse(HttpStatus status) {
    var page = new Page<Dashboard>();
    var dashboard = new Dashboard();
    dashboard.setId(1);
    page.setResults(List.of(dashboard));

    return new ResponseEntity<>(page, status);
  }
}
