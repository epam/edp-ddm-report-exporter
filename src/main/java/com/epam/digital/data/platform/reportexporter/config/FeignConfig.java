package com.epam.digital.data.platform.reportexporter.config;

import com.epam.digital.data.platform.reportexporter.config.feign.FeignErrorDecoder;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.Retryer.Default;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

  private final OpenShiftClient client;

  public FeignConfig(OpenShiftClient client) {
    this.client = client;
  }

  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate ->
        requestTemplate.header("Authorization", client.getApiKey());
  }

  @Bean
  public ErrorDecoder errorDecoder() {
    return new FeignErrorDecoder();
  }

  @Bean
  public Retryer retryer() {
    return new Default();
  }
}
