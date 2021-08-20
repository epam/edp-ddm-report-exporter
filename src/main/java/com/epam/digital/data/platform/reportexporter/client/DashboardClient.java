package com.epam.digital.data.platform.reportexporter.client;

import com.epam.digital.data.platform.reportexporter.config.FeignConfig;
import com.epam.digital.data.platform.reportexporter.model.Dashboard;
import com.epam.digital.data.platform.reportexporter.model.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "dashboard", url = "${redash.url}", configuration = FeignConfig.class)
public interface DashboardClient {

  @GetMapping("/api/dashboards")
  ResponseEntity<Page<Dashboard>> getDashboards();

  @GetMapping("/api/dashboards/{slug}")
  ResponseEntity<Dashboard> getDashboardBySlug(@PathVariable("slug") String slug);
}
