package com.epam.digital.data.platform.reportexporter.exception;

import static com.epam.digital.data.platform.reportexporter.util.Header.TRACE_ID;

import com.epam.digital.data.platform.reportexporter.model.exception.DetailedErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

  private final String RUNTIME_ERROR = "RUNTIME_ERROR";
  private final String NOT_FOUND = "NOT_FOUND";
  private final String DASHBOARD_ZIP_ERROR = "DASHBOARD_ZIP_ERROR";
  private final String FORBIDDEN_OPERATION = "FORBIDDEN_OPERATION";

  @ExceptionHandler(Exception.class)
  public ResponseEntity<DetailedErrorResponse<Void>> handleException(Exception exception) {
    log.error("Runtime error occurred", exception);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(newDetailedResponse(RUNTIME_ERROR));
  }

  @ExceptionHandler(DashboardZippingException.class)
  public ResponseEntity<DetailedErrorResponse<Void>> handleDashboardZippingException(
      DashboardZippingException exception) {
    log.error("Error during zipping dashboard with queries", exception);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(newDetailedResponse(DASHBOARD_ZIP_ERROR));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<DetailedErrorResponse<Void>> handleAccessDeniedException(
      AccessDeniedException exception) {
    log.error("Access denied", exception);
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(newDetailedResponse(FORBIDDEN_OPERATION));
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<DetailedErrorResponse<Void>> handleNotFoundException(
      NotFoundException exception) {
    log.error("Redash object not found", exception);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(newDetailedResponse(NOT_FOUND));
  }

  private <T> DetailedErrorResponse<T> newDetailedResponse(String code) {
    var response = new DetailedErrorResponse<T>();
    response.setTraceId(MDC.get(TRACE_ID.getHeaderName()));
    response.setCode(code);
    return response;
  }
}
