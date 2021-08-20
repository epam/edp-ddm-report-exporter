package com.epam.digital.data.platform.reportexporter.model.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class DetailedErrorResponse<T> {
  private String traceId;
  private String code;
  private T details;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getTraceId() {
    return traceId;
  }

  public void setTraceId(String traceId) {
    this.traceId = traceId;
  }

  @JsonInclude(Include.NON_NULL)
  public T getDetails() {
    return details;
  }

  public void setDetails(T details) {
    this.details = details;
  }
}
