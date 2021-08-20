package com.epam.digital.data.platform.reportexporter.service;

import static com.epam.digital.data.platform.reportexporter.util.QueryFormatter.formatQueryList;
import static com.epam.digital.data.platform.reportexporter.util.ResponseHandler.handleResponse;
import com.epam.digital.data.platform.reportexporter.client.DashboardClient;
import com.epam.digital.data.platform.reportexporter.model.Dashboard;
import java.util.List;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

//TODO: think about better name for service
@Service
public class ReportService {

  private final DashboardClient dashboardClient;

  private final DashboardArchiver archiver;
  private final QueryHelper queryHelper;

  public ReportService(DashboardClient client,
      DashboardArchiver archiver,
      QueryHelper queryHelper) {
    this.dashboardClient = client;
    this.archiver = archiver;
    this.queryHelper = queryHelper;
  }

  public List<Dashboard> getDashboards() {
    return handleResponse(dashboardClient.getDashboards()).getResults();
  }

  public ByteArrayResource getArchive(String slug) {
    var dashboard = handleResponse(dashboardClient.getDashboardBySlug(slug));
    var queries = queryHelper.getUtilQueries(dashboard);

    return archiver.zipDashboard(slug, formatQueryList(queries), dashboard);
  }

}
