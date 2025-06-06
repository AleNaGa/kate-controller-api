package com.vedruna.kate_controller_api.service;

import com.vedruna.kate_controller_api.dto.ClusterStateDTO;

import io.fabric8.kubernetes.client.KubernetesClient;

public interface ClusterServiceI {

    public ClusterStateDTO getClusterState(String apiServerUrl, String token);
    
}
