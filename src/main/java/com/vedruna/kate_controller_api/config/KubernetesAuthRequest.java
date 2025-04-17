package com.vedruna.kate_controller_api.config;

import lombok.Data;

@Data
public class KubernetesAuthRequest {
    private String apiServerUrl;
    private String token;
}

