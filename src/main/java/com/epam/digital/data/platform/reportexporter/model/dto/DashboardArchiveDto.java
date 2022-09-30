/*
 * Copyright 2022 EPAM Systems.
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

package com.epam.digital.data.platform.reportexporter.model.dto;

import org.springframework.core.io.ByteArrayResource;

public class DashboardArchiveDto {
  private final String dashboardSlug;
  private final ByteArrayResource archive;

  public DashboardArchiveDto(String dashboardSlug, ByteArrayResource archive) {
    this.dashboardSlug = dashboardSlug;
    this.archive = archive;
  }

  public String getDashboardSlug() {
    return dashboardSlug;
  }

  public ByteArrayResource getArchive() {
    return archive;
  }
}
