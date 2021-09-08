package com.epam.digital.data.platform.reportexporter.config.feign;

import com.epam.digital.data.platform.reportexporter.exception.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class FeignErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    var status = HttpStatus.valueOf(response.status());

    if (status.equals(HttpStatus.NOT_FOUND)) {
      throw new NotFoundException("Redash object not found");
    } else {
      throw new RuntimeException("Runtime error during call to Redash occurred");
    }
  }
}