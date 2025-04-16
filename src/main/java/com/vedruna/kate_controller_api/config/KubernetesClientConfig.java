package com.vedruna.kate_controller_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

@Configuration
public class KubernetesClientConfig {

   @Bean
    public KubernetesClient kubernetesClient() {
        return new DefaultKubernetesClient(); // Usar kubeconfig por defecto
    }
    
}
