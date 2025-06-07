package com.vedruna.kate_controller_api.config;

import lombok.Data;

@Data
public class KubernetesAuthRequest {
    // Información de autenticación para el cluster, limpieza de código
    private String apiServerUrl;
    private String token;
}
