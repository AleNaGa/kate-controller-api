package com.vedruna.kate_controller_api.service;

import org.springframework.stereotype.Service;

import com.vedruna.kate_controller_api.dto.ClusterStateDTO;
import com.vedruna.kate_controller_api.config.KubernetesClientFactory;
import com.vedruna.kate_controller_api.mapper.ClusterStateMapper;

import io.fabric8.kubernetes.client.KubernetesClient;

@Service
public class ClusterServiceImpl implements ClusterServiceI {

    @Override
    public ClusterStateDTO getClusterState(String apiServerUrl, String token) {
        try (KubernetesClient client = KubernetesClientFactory.getClient(apiServerUrl, token)) {
            return ClusterStateMapper.buildClusterState(client);
        }
    }
}