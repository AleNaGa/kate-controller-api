package com.vedruna.kate_controller_api.services;

import org.springframework.stereotype.Service;

import com.vedruna.kate_controller_api.dto.ClusterStateDTO;
import com.vedruna.kate_controller_api.config.KubernetesClientFactory;
import com.vedruna.kate_controller_api.mapper.ClusterStateMapper;

import io.fabric8.kubernetes.client.KubernetesClient;

@Service
public class ClusterServiceImpl implements ClusterServiceI {

    @Override
    public ClusterStateDTO getClusterState(String apiServerUrl, String token) {
        // Crear el cliente con la URL del API y el token que vienen del front
        KubernetesClient client = KubernetesClientFactory.getClient(apiServerUrl, token);

        // Construir el DTO con los datos del cluster
        return ClusterStateMapper.buildClusterState(client);
    }
}