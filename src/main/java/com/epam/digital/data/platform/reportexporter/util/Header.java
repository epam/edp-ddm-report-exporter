package com.epam.digital.data.platform.reportexporter.util;

public enum Header {
  TRACE_ID("X-B3-TraceId"),
  ACCESS_TOKEN("X-Access-Token");

  private final String headerName;

  Header(String headerName) {
    this.headerName = headerName;
  }

  public String getHeaderName() {
    return headerName;
  }
}
