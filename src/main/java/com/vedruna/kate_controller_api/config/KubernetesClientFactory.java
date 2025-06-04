package com.vedruna.kate_controller_api.config;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.Data;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;

@Data
public class KubernetesClientFactory {

    public static KubernetesClient getClient(String apiServerUrl, String token) {
        Config config = new ConfigBuilder()
                .withMasterUrl(apiServerUrl.trim())
                .withOauthToken(token)
                .withTrustCerts(true) // Certificación válida
                .build();

        return new DefaultKubernetesClient(config);
    }
}
