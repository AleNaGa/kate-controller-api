package com.vedruna.kate_controller_api.mapper;

import java.util.List;

import com.vedruna.kate_controller_api.dto.ClusterStateDTO;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;

public class ClusterStateMapper {

    public static ClusterStateDTO buildClusterState(KubernetesClient client) {
        ClusterStateDTO dto = new ClusterStateDTO();

        // Namespaces
        dto.setNamespaces(
            client.namespaces().list().getItems()
                .stream()
                .map(NamespaceMapper::toDTO)
                .toList()
        );

        // Deployments (de todos los namespaces)
        dto.setDeployments(
            client.apps().deployments().inAnyNamespace().list().getItems()
                .stream()
                .map(DeploymentMapper::toDTO)
                .toList()
        );

        // Services (de todos los namespaces)
        dto.setServices(
            client.services().inAnyNamespace().list().getItems()
                .stream()
                .map(ServiceMapper::toDTO)
                .toList()
        );

        // Pods (de todos los namespaces)
        List<Pod> allPods = client.pods().inAnyNamespace().list().getItems();

        // Nodes + pods en ese nodo
        dto.setNodes(
            client.nodes().list().getItems()
                .stream()
                .map(node -> {
                    String nodeName = node.getMetadata().getName();
                    List<Pod> podsInNode = allPods.stream()
                        .filter(p -> p.getSpec().getNodeName() != null &&
                                     p.getSpec().getNodeName().equals(nodeName))
                        .toList();
                    return NodeMapper.toDTO(node, podsInNode);
                })
                .toList()
        );

        return dto;
    }
}
