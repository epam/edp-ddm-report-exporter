package com.epam.digital.data.platform.reportexporter.exception;

import java.util.UUID;
import javax.validation.constraints.NotBlank;

public class MockEntity {
  private UUID entityId;
  @NotBlank
  private String excerptType;

  public UUID getEntityId() {
    return entityId;
  }

  public void setEntityId(UUID entityId) {
    this.entityId = entityId;
  }

  public String getExcerptType() {
    return excerptType;
  }

  public void setExcerptType(String excerptType) {
    this.excerptType = excerptType;
  }

}
