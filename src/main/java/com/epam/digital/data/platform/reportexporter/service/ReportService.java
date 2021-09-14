package com.epam.digital.data.platform.reportexporter.service;

import static com.epam.digital.data.platform.reportexporter.util.QueryFormatter.formatQueryList;
import static com.epam.digital.data.platform.reportexporter.util.ResponseHandler.handleResponse;
import static java.util.stream.Collectors.toList;

import com.epam.digital.data.platform.reportexporter.client.DashboardClient;
import com.epam.digital.data.platform.reportexporter.model.Dashboard;
import java.util.List;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

  private final DashboardClient dashboardClient;

  private final DashboardArchiver archiver;
  private final QueryHelper queryHelper;

  public ReportService(DashboardClient client,
      QueryHelper queryHelper,
      DashboardArchiver archiver) {
    this.dashboardClient = client;
    this.archiver = archiver;
    this.queryHelper = queryHelper;
  }

  public List<Dashboard> getDashboards() {
    var dashboards =  handleResponse(dashboardClient.getDashboards()).getResults();

    return dashboards.stream()
        .filter(dashboard -> !dashboard.isDraft())
        .collect(toList());
  }

  public ByteArrayResource getArchive(String slug) {
    var dashboard = handleResponse(dashboardClient.getDashboardBySlug(slug));
    var queries = queryHelper.getUtilQueries(dashboard);

    return archiver.zipDashboard(slug, formatQueryList(queries), dashboard);
  }

}
