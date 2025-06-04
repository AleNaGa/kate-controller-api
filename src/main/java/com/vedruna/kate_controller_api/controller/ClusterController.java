package com.vedruna.kate_controller_api.controller;


import com.vedruna.kate_controller_api.config.KubernetesAuthRequest;
import com.vedruna.kate_controller_api.config.KubernetesClientFactory;
import com.vedruna.kate_controller_api.dto.ClusterStateDTO;
import com.vedruna.kate_controller_api.mapper.ClusterStateMapper;
import com.vedruna.kate_controller_api.services.ClusterServiceI;
import com.vedruna.kate_controller_api.session.ClusterSessionManager;

import io.fabric8.kubernetes.client.KubernetesClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/cluster") 
public class ClusterController {


      @Autowired
    private ClusterServiceI clusterService;

    @Autowired
    private ClusterSessionManager sessionManager;

    // Prueba de conexión
    @GetMapping("/test")
    public ResponseEntity<String> testConnection(@RequestBody KubernetesAuthRequest authRequest) {
        try {
            System.out.println("API Server URL: " + authRequest.getApiServerUrl());
            KubernetesClient client = KubernetesClientFactory.getClient(authRequest.getApiServerUrl(), authRequest.getToken());
            String version = client.getVersion().getGitVersion();
            return ResponseEntity.ok("Connected to Kubernetes v" + version);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: " + e.getMessage());
        }
    }


    // 1. Login y verificación del token
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody KubernetesAuthRequest authRequest) {
        try {
            // Verificar que el token y la URL funcionan
            KubernetesClient client = KubernetesClientFactory.getClient(authRequest.getApiServerUrl(), authRequest.getToken());
            client.namespaces().list(); // simple llamada de prueba

            // Crear sesión
            String sessionId = sessionManager.createSession(authRequest);
            return ResponseEntity.ok(sessionId);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Token o URL inválidos.");
        }
    }



    // estado completo del Cluster
   // 2. Obtener el estado del cluster con sessionId
    @GetMapping("/state")
    public ResponseEntity<ClusterStateDTO> getClusterState(
            @RequestHeader("X-Session-Id") String sessionId) {

        KubernetesAuthRequest authRequest = sessionManager.getAuthRequest(sessionId);

        if (authRequest == null) {
            return ResponseEntity.status(401).body(null); // Sesión no válida
        }

        try {
            ClusterStateDTO state = clusterService.getClusterState(
                    authRequest.getApiServerUrl(),
                    authRequest.getToken());

            return ResponseEntity.ok(state);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Error interno
        }
    }


}

