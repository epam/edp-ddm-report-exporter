package com.epam.digital.data.platform.reportexporter.service;

import com.epam.digital.data.platform.reportexporter.exception.DashboardZippingException;
import com.epam.digital.data.platform.reportexporter.model.Dashboard;
import com.epam.digital.data.platform.reportexporter.model.Page;
import com.epam.digital.data.platform.reportexporter.model.Query;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

@Component
public class DashboardArchiver {

  private final Logger log = LoggerFactory.getLogger(DashboardArchiver.class);

  private final String ZIP_FILE_NAME = "dashboard_%s.zip";
  private final String QUERY_FILE_NAME = "queries/queries_%s.json";
  private final String DASHBOARD_FILE_NAME = "dashboard_%s.json";

  private final ObjectMapper objectMapper;

  public DashboardArchiver(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }
  
  public ByteArrayResource zipDashboard(String slug, Page<Query> queries, Dashboard dashboard) {
    log.info("Creating zip archive for dashboard with slug {}", slug);
    var zip = zip(slug, queries, dashboard);

    try {
      var zipBytes = FileUtils.readFileToByteArray(zip);
      return new ByteArrayResource(zipBytes);
    } catch (Exception e) {
      log.error("Error during converting zip to byte array for dashboard slug {}", slug);
      throw new DashboardZippingException("Could not zip dashboard", e);
    } finally {
      zip.delete();
    }
  }

  private File zip(String slug, Page<Query> queries, Dashboard dashboard) {
    var zip = new File(String.format(ZIP_FILE_NAME, FilenameUtils.getName(slug)));

    try (var zipStream = new ZipOutputStream(new FileOutputStream(zip))) {
      zipQuery(zipStream, queries, slug);
      zipDashboard(zipStream, dashboard, slug);
    } catch (Exception e) {
      log.error("Error during creating zip archive for dashboard slug {}", slug);
      throw new DashboardZippingException("Could not zip dashboard", e);
    }

    return zip;
  }

  private void zipQuery(ZipOutputStream zipStream, Page<Query> queries, String slug)
      throws IOException {
    log.info("Putting query file into zip archive");
    var queryFile = new ZipEntry(String.format(QUERY_FILE_NAME, FilenameUtils.getName(slug)));
    var queryData = objectMapper.writeValueAsString(queries).getBytes(StandardCharsets.UTF_8);

    zipStream.putNextEntry(queryFile);
    zipStream.write(queryData, 0, queryData.length);
    zipStream.closeEntry();
  }

  private void zipDashboard(ZipOutputStream zipStream, Dashboard dashboard, String slug)
      throws IOException {
    log.info("Putting dashboard file into zip archive");
    var dashboardFile = new ZipEntry(String.format(DASHBOARD_FILE_NAME, FilenameUtils.getName(slug)));
    var dashboardData = objectMapper.writeValueAsString(dashboard).getBytes(StandardCharsets.UTF_8);

    zipStream.putNextEntry(dashboardFile);
    zipStream.write(dashboardData, 0, dashboardData.length);
    zipStream.closeEntry();
  }
}
