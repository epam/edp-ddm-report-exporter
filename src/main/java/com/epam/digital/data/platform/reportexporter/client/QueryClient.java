package com.epam.digital.data.platform.reportexporter.client;

import com.epam.digital.data.platform.reportexporter.config.FeignConfig;
import com.epam.digital.data.platform.reportexporter.model.Page;
import com.epam.digital.data.platform.reportexporter.model.Query;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "query", url = "${redash.url}", configuration = FeignConfig.class)
public interface QueryClient {

  @GetMapping("/api/queries")
  ResponseEntity<Page<Query>> getQueries(@RequestParam("page_size") int pageSize);
}
