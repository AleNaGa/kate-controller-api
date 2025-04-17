package com.vedruna.kate_controller_api.controller;


import com.vedruna.kate_controller_api.config.KubernetesAuthRequest;
import com.vedruna.kate_controller_api.config.KubernetesClientFactory;
import com.vedruna.kate_controller_api.dto.ClusterStateDTO;
import com.vedruna.kate_controller_api.mapper.ClusterStateMapper;
import com.vedruna.kate_controller_api.services.ClusterServiceI;

import io.fabric8.kubernetes.client.KubernetesClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cluster")  // Esta es la ruta base del controlador
public class ClusterController {

    // Inyección del Servicio
    @Autowired
    private ClusterServiceI clusterServ;


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




    // Estado completo del Cluster
    @GetMapping("/{clusterName}/state")
   public ResponseEntity<ClusterStateDTO> getClusterState(@RequestBody KubernetesAuthRequest authRequest) {
    try {
        System.out.println("API Server URL: " + authRequest.getApiServerUrl());
        ClusterStateDTO state = clusterServ.getClusterState(authRequest.getApiServerUrl(), authRequest.getToken());
            return ResponseEntity.ok(state);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}

