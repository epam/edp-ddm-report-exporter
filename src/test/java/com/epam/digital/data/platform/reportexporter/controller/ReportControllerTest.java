/*
 * Copyright 2021 EPAM Systems.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.digital.data.platform.reportexporter.controller;

import static java.util.List.of;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.digital.data.platform.reportexporter.model.Dashboard;
import com.epam.digital.data.platform.reportexporter.model.dto.DashboardArchiveDto;
import com.epam.digital.data.platform.reportexporter.service.ReportService;
import java.util.Base64;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@ControllerTest(ReportController.class)
class ReportControllerTest {

  static final String BASE_URL = "/reports";

  static final Long DASHBOARD_ID = 1L;
  static final String DASHBOARD_SLUG_ID = "stub_slug";
  static final String ENCODED_STRING = Base64.getEncoder().encodeToString("test".getBytes());

  static final String HEADER_NAME = "Content-Disposition";
  static final String HEADER_VALUE = "attachment; filename=\"dashboard_stub_slug.zip\"";

  @Autowired
  MockMvc mockMvc;

  @MockBean
  ReportService reportService;

  @Test
  void getDashboards() throws Exception {
    var dashboards = stubDashboards();
    when(reportService.getDashboards()).thenReturn(dashboards);

    mockMvc.perform(get(BASE_URL))
        .andExpect(matchAll(
            status().isOk(),
            content().contentType(MediaType.APPLICATION_JSON),
            jsonPath("$[0].name", is("stub name")),
            jsonPath("$[0].slug", is("stub_slug")))
        );
  }

  @Test
  void getArchive() throws Exception {
    when(reportService.getArchive(any()))
        .thenReturn(new DashboardArchiveDto(DASHBOARD_SLUG_ID, new ByteArrayResource(Base64.getDecoder().decode(ENCODED_STRING))));

    mockMvc.perform(get(BASE_URL + "/{id}", DASHBOARD_ID))
        .andExpect(matchAll(
            status().isOk(),
            content().contentType(MediaType.APPLICATION_OCTET_STREAM),
            header().string(HEADER_NAME, HEADER_VALUE))
        );
  }

  private List<Dashboard> stubDashboards() {
    var dashboard = new Dashboard();
    dashboard.setName("stub name");
    dashboard.setSlug("stub_slug");
    return of(dashboard);
  }
}
