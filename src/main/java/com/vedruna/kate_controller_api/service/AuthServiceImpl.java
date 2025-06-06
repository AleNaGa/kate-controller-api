package com.vedruna.kate_controller_api.service;

import org.springframework.stereotype.Service;

import com.vedruna.kate_controller_api.config.KubernetesClientFactory;

import io.fabric8.kubernetes.client.KubernetesClient;


@Service
public class AuthServiceImpl implements AuthServiceI {
    public void verifyConnection(String apiServerUrl, String token) {
        try (KubernetesClient client = KubernetesClientFactory.getClient(apiServerUrl, token)) {
            client.namespaces().list(); // Verificar que el token y la URL funcionan
        }
    }
}
