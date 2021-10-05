package com.epam.digital.data.platform.reportexporter.config;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.openshift.client.DefaultOpenShiftClient;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenShiftConfiguration {

  @Value("${openshift.namespace}")
  private String namespace;

  @Bean
  public Supplier<KubernetesClient> kubernetesClientFactory() {
    return () -> new DefaultOpenShiftClient().inNamespace(namespace);
  }
}