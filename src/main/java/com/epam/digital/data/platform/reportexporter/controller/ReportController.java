package com.epam.digital.data.platform.reportexporter.controller;

import com.epam.digital.data.platform.reportexporter.model.Dashboard;
import java.util.List;
import com.epam.digital.data.platform.reportexporter.service.ReportService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
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

  private final ReportService service;

  public ReportController(
      ReportService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Dashboard>> getAllDashboards() {
    return ResponseEntity.ok().body(service.getDashboards());
  }

  @GetMapping(path = "/{slug_id}", produces = "application/zip")
  public ResponseEntity<Resource> getDashboardArchive(@PathVariable("slug_id") String slugId) {
    var zip = service.getArchive(slugId);

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .contentLength(zip.getByteArray().length)
        .header(CONTENT_DISPOSITION_HEADER_NAME, String.format(ATTACHMENT_HEADER_VALUE, slugId))
        .body(zip);
  }
}
