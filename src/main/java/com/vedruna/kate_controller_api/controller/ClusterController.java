package com.vedruna.kate_controller_api.controller;


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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cluster")  // Esta es la ruta base del controlador
public class ClusterController {

    // Inyección del Servicio
    @Autowired
    private ClusterServiceI clusterServ;


    // Estado completo del Cluster
    @GetMapping("/{clusterName}/state")
   public ResponseEntity<ClusterStateDTO> getClusterState(
        @RequestParam String apiServerUrl,
        @RequestParam String token
    ) {
        try {
            ClusterStateDTO state = clusterServ.getClusterState(apiServerUrl, token);
            return ResponseEntity.ok(state);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}

