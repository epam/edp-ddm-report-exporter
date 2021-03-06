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

package com.epam.digital.data.platform.reportexporter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Query {
  private Integer id;
  private Integer dataSourceId;
  private String name;
  private String query;
  private String description;
  @JsonInclude(Include.NON_EMPTY)
  private Map<String, Object> schedule;
  private Map<String, Object> options;
  @JsonProperty(access = Access.WRITE_ONLY)
  private List<Visualization> visualizations;
  private boolean isDraft;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getDataSourceId() {
    return dataSourceId;
  }

  public void setDataSourceId(Integer dataSourceId) {
    this.dataSourceId = dataSourceId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Map<String, Object> getSchedule() {
    return schedule;
  }

  public void setSchedule(Map<String, Object> schedule) {
    this.schedule = schedule;
  }

  public Map<String, Object> getOptions() {
    return options;
  }

  public void setOptions(Map<String, Object> options) {
    this.options = options;
  }

  public List<Visualization> getVisualizations() {
    return visualizations;
  }

  public void setVisualizations(
      List<Visualization> visualizations) {
    this.visualizations = visualizations;
  }

  public boolean isDraft() {
    return isDraft;
  }

  public void setDraft(boolean draft) {
    isDraft = draft;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Query query1 = (Query) o;
    return name.equals(query1.name) && query.equals(query1.query) && Objects
        .equals(description, query1.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, query, description);
  }
}
