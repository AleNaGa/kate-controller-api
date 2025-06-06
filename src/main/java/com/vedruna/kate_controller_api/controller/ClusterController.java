package com.vedruna.kate_controller_api.controller;

import com.vedruna.kate_controller_api.config.KubernetesAuthRequest;
import com.vedruna.kate_controller_api.dto.ClusterStateDTO;
import com.vedruna.kate_controller_api.service.AuthServiceI;
import com.vedruna.kate_controller_api.service.ClusterServiceI;
import com.vedruna.kate_controller_api.session.ClusterSessionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cluster")
public class ClusterController {

    @Autowired
    private ClusterServiceI clusterService;

    @Autowired
    private AuthServiceI authService;

    @Autowired
    private ClusterSessionManager sessionManager;

    /**
     * Autenticación en el cluster de kubernetes
     *
     * @param authRequest Información de autenticación:
     *                    - apiServerUrl: URL del servidor de API de kubernetes
     *                    - token: Token de autenticación
     *
     * @return Id de sesión en caso de autenticación exitosa
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody KubernetesAuthRequest authRequest) {
        authService.verifyConnection(authRequest.getApiServerUrl(), authRequest.getToken());
        String sessionId = sessionManager.createSession(authRequest);
        return ResponseEntity.ok(sessionId);
    }

    /**
     * Verifica la conexión a un cluster de kubernetes
     *
     * @param authRequest Información de autenticación:
     *                    - apiServerUrl: URL del servidor de API de kubernetes
     *                    - token: Token de autenticación
     *
     * @return Mensaje de "Conexión a Kubernetes exitosa." en caso de éxito
     */
    @GetMapping("/test")
    public ResponseEntity<String> testConnection(@RequestBody KubernetesAuthRequest authRequest) {
        authService.verifyConnection(authRequest.getApiServerUrl(), authRequest.getToken());
        return ResponseEntity.ok("Conexión a Kubernetes exitosa.");
    }

    /**
     * Obtiene el estado actual del cluster de Kubernetes.
     *
     * @param sessionId Id de la sesión para recuperar la información de autenticación
     * @return ResponseEntity con el estado del cluster si la sesión es válida,
     *         o un estado 401 si la sesión no es válida.
     */

    @GetMapping("/state")
    public ResponseEntity<ClusterStateDTO> getClusterState(@RequestHeader("X-Session-Id") String sessionId) {
        var authRequest = sessionManager.getAuthRequest(sessionId);
        if (authRequest == null) return ResponseEntity.status(401).build();

        ClusterStateDTO state = clusterService.getClusterState(
                authRequest.getApiServerUrl(),
                authRequest.getToken());

        return ResponseEntity.ok(state);
    }
}
