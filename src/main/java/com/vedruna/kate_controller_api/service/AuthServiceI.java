package com.vedruna.kate_controller_api.service;


public interface AuthServiceI {
    // Verificar que el token y la URL funcionan a través de la API
    void verifyConnection(String apiServerUrl, String token);
}
