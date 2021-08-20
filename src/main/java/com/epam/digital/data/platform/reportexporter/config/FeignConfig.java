package com.epam.digital.data.platform.reportexporter.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class FeignConfig {

  //TODO: obtain api key from OpenShift
  @Value("${redash.api-key}")
  private String apiKey;

  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate ->
        requestTemplate.header("Authorization", apiKey);
  }
}
