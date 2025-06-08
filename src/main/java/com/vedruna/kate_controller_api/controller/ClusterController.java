package com.vedruna.kate_controller_api.controller;

import com.vedruna.kate_controller_api.config.KubernetesAuthRequest;
import com.vedruna.kate_controller_api.dto.ClusterStateDTO;
import com.vedruna.kate_controller_api.service.AuthServiceI;
import com.vedruna.kate_controller_api.service.ClusterServiceI;
import com.vedruna.kate_controller_api.session.ClusterSessionManager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
     @Operation(summary = "Autenticación en el cluster de Kubernetes",
        description = "Recibe los datos de autenticación y devuelve un Id de sesión.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Autenticación exitosa"),
        @ApiResponse(responseCode = "401", description = "Autenticación fallida")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody KubernetesAuthRequest authRequest) {
        authService.verifyConnection(authRequest.getApiServerUrl(), authRequest.getToken());
        String sessionId = sessionManager.createSession(authRequest);
        return ResponseEntity.ok(sessionId);
    }

    /**
     * Verifica la conexión a la api
     *
     * @return Mensaje de "Conexión exitosa." en caso de éxito
     */
    @Operation(summary = "Prueba de conexión al API")
    @GetMapping("/test")
    public ResponseEntity<String> testConnection() {
        return ResponseEntity.ok("Conexión exitosa.");
    }

    /**
     * Obtiene el estado actual del cluster de Kubernetes.
     *
     * @param sessionId Id de la sesión para recuperar la información de autenticación
     * @return ResponseEntity con el estado del cluster si la sesión es válida,
     *         o un estado 401 si la sesión no es válida.
     */
    @Operation(summary = "Obtiene el estado actual del cluster de Kubernetes",
        description = "Requiere un header 'X-Session-Id' con la sesión válida.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado del cluster obtenido"),
        @ApiResponse(responseCode = "401", description = "Sesión no válida")
    })
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
