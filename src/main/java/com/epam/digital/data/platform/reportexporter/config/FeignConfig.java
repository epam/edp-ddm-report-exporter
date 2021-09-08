package com.epam.digital.data.platform.reportexporter.config;

import com.epam.digital.data.platform.reportexporter.config.feign.FeignErrorDecoder;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class FeignConfig {

  @Value("${redash.api-key}")
  private String apiKey;

  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate ->
        requestTemplate.header("Authorization", apiKey);
  }

  @Bean
  public ErrorDecoder errorDecoder() {
    return new FeignErrorDecoder();
  }

}
