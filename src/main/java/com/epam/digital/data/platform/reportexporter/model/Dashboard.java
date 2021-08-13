package com.epam.digital.data.platform.reportexporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Set;

public class Dashboard {
  private Integer id;
  private String name;
  private String slug;
  private List<String> tags;
  private Set<Widget> widgets;
  private Object options;
  @JsonProperty("is_draft")
  private boolean isDraft;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public Set<Widget> getWidgets() {
    return widgets;
  }

  public void setWidgets(Set<Widget> widgets) {
    this.widgets = widgets;
  }

  public Object getOptions() {
    return options;
  }

  public void setOptions(Object options) {
    this.options = options;
  }

  public boolean isDraft() {
    return isDraft;
  }

  public void setDraft(boolean draft) {
    isDraft = draft;
  }
}
