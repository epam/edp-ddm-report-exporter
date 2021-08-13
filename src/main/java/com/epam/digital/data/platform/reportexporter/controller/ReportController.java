package com.epam.digital.data.platform.reportexporter.controller;

import com.epam.digital.data.platform.reportexporter.model.Dashboard;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {

  private static final String CONTENT_DISPOSITION_HEADER_NAME = "Content-Disposition";
  private static final String ATTACHMENT_HEADER_VALUE = "attachment; filename=\"dashboard_%s.zip\"";

  @GetMapping
  public ResponseEntity<List<Dashboard>> getAllDashboards() {
    var dashboard = new Dashboard();
    dashboard.setSlug("stub");
    dashboard.setName("stub dashboard");
    return ResponseEntity.ok().body(List.of(dashboard));
  }

  //TODO: implement
  @GetMapping(path = "/{slug_id}", produces = "application/zip")
  public ResponseEntity<Resource> getDashboardArchive(@PathVariable("slug_id") String slugId)
      throws IOException {
    var zip = IOUtils.toByteArray(ReportController.class.getResourceAsStream("/dashboard.zip"));

    return ResponseEntity.ok()
        .header(CONTENT_DISPOSITION_HEADER_NAME, String.format(ATTACHMENT_HEADER_VALUE, slugId))
        .body(new ByteArrayResource(zip));

  }
}
