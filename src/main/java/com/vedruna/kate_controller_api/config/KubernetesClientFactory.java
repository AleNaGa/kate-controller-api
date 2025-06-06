package com.vedruna.kate_controller_api.config;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class KubernetesClientFactory {

    public static KubernetesClient getClient(String apiServerUrl, String token) {
         if (token == null || token.trim().isEmpty()) {
        throw new IllegalArgumentException("El token de acceso no puede estar vacío.");
    }

     Config config = new ConfigBuilder()
            .withMasterUrl(apiServerUrl.trim())
            .withOauthToken(token.trim())
            .withTrustCerts(true)
            .withNamespace(null) 
            .build();

    return new DefaultKubernetesClient(config);
    }
}
