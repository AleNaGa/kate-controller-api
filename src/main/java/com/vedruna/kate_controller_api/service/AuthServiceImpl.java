package com.vedruna.kate_controller_api.service;

import org.springframework.stereotype.Service;

import com.vedruna.kate_controller_api.config.KubernetesClientFactory;

import io.fabric8.kubernetes.client.KubernetesClient;


@Service
public class AuthServiceImpl implements AuthServiceI {
/**
 * Verificar que el token y la URL funcionan a través de la API de Kubernetes
 * con la ayuda de la clase KubernetesClientFactory
 *
 * @param apiServerUrl La URL del servidor de API de Kubernetes
 * @param token El token de autenticación
 * @throws KubernetesClientException Si ocurre un error al verificar la conexión
 */

    public void verifyConnection(String apiServerUrl, String token) {
        try (KubernetesClient client = KubernetesClientFactory.getClient(apiServerUrl, token)) {
            client.namespaces().list(); // Verificar que el token y la URL funcionan
        }
    }
}
