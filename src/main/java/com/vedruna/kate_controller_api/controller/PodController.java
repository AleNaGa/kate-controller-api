package com.vedruna.kate_controller_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.kate_controller_api.config.KubernetesAuthRequest;
import com.vedruna.kate_controller_api.dto.PodScaleRequestDTO;
import com.vedruna.kate_controller_api.service.PodServiceI;
import com.vedruna.kate_controller_api.session.ClusterSessionManager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/pod")
public class PodController {
     @Autowired
    private PodServiceI podService;

    @Autowired
    private ClusterSessionManager sessionManager;

    /**
     * Escala un deployment en un namespace y con un nombre dado a 0 réplicas.
     * Básicamente "apaga" el deployment
     *
     * @param sessionId Id de sesióm
     * @param request Información del deployment a escalar:
     *                - namespace: Namespace en el que se encuentra el deployment
     *                - deploymentName: Nombre del deployment a escalar
     *
     * @return Mensaje de "Deployment escalado a 0 rplicas correctamente" en caso de éxito
     */
    @Operation(summary = "Escala un deployment a 0 réplicas (apagar deployment)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Deployment escalado a 0 réplicas correctamente"),
        @ApiResponse(responseCode = "401", description = "Sesión no válida")
    })
    @PostMapping("/scale-down")
    public ResponseEntity<String> scaleDownDeployment(
            @RequestHeader("X-Session-Id") String sessionId,
            @RequestBody PodScaleRequestDTO request) {

        KubernetesAuthRequest auth = sessionManager.getAuthRequest(sessionId);
        if (auth == null) return ResponseEntity.status(401).body("Sesión no válida");

        podService.scaleDeploymentToZero(
                auth.getApiServerUrl(),
                auth.getToken(),
                request.getNamespace(),
                request.getDeploymentName());

        return ResponseEntity.ok("Deployment escalado a 0 réplicas correctamente.");
    }


    /**
     * Escala un deployment en un namespace y con un nombre dado a x réplicas.
     * Básicamente "enciende" el deployment
     *
     * @param sessionId Id de sesión
     * @param request Información del deployment a escalar:
     *                - namespace: Namespace en el que se encuentra el deployment
     *                - deploymentName: Nombre del deployment a escalar
     *                - replicas: Número de réplicas
     *
     * @return Mensaje de "Deployment escalado a x réplica correctamente." en caso de éxito
     */
    @Operation(summary = "Escala un deployment a un número determinado de réplicas (enciende el deployment)")
        @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Deployment escalado correctamente"),
        @ApiResponse(responseCode = "401", description = "Sesión no válida")
        })
    @PostMapping("/scale-up")
    public ResponseEntity<String> scaleUpDeployment(
            @RequestHeader("X-Session-Id") String sessionId,
            @RequestBody PodScaleRequestDTO request) {

        KubernetesAuthRequest auth = sessionManager.getAuthRequest(sessionId);
        if (auth == null) return ResponseEntity.status(401).body("Sesión no válida");

        podService.scaleDeployment(
                auth.getApiServerUrl(),
                auth.getToken(),
                request.getNamespace(),
                request.getDeploymentName(), 
                request.getReplicas());// Escala a x réplicas

        return ResponseEntity.ok("Deployment escalado a "+ request.getReplicas() + " réplicas correctamente.");
    }
}
