package com.vedruna.kate_controller_api.config;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class KubernetesClientFactory {

    /**
     * Crea un cliente de Kubernetes que se puede utilizar para interactuar con la API de Kubernetes
     * en el cluster especificado por el parámetro {@code apiServerUrl} y autenticado con el token 
     * especificado por el parmetro {@code token}.

     * @param apiServerUrl la URL del servidor de API de Kubernetes
     * @param token el token de autenticaci n a utilizar
     * @return un cliente de Kubernetes configurado correctamente
     * @throws IllegalArgumentException si el token de autenticaci n es nulo o vac o
     */
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
