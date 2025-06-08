package com.vedruna.kate_controller_api.mapper;

import java.util.List;

import com.vedruna.kate_controller_api.dto.NodeDTO;

import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeAddress;
import io.fabric8.kubernetes.api.model.NodeCondition;
import io.fabric8.kubernetes.api.model.Pod;

public class NodeMapper {
    /**
     * Convierte un objeto Node de Kubernetes en un objeto NodeDTO, que es el que se devuelve
     * en la API de Kate.
     * @param node el objeto Node que se va a convertir
     * @param podsInNode lista de Pods que están corriendo en el nodo
     * @return el objeto NodeDTO que se ha creado
     */
    public static NodeDTO toDTO(Node node, List<Pod> podsInNode) {
        NodeDTO dto = new NodeDTO();
        dto.setName(node.getMetadata().getName());
        //se hace un stream y se filtra por el tipo de condición "Ready"
        dto.setStatus(node.getStatus().getConditions()
            .stream()
            .filter(c -> c.getType().equals("Ready"))
            .findFirst()
            .map(NodeCondition::getStatus)
            .orElse("Unknown"));

        // se hace un stream y se filtra por el tipo de dirección "InternalIP"
        dto.setIp(node.getStatus().getAddresses()
            .stream()
            .filter(a -> a.getType().equals("InternalIP"))
            .findFirst()
            .map(NodeAddress::getAddress)
            .orElse("N/A"));

        dto.setCpu(node.getStatus().getCapacity().get("cpu").getAmount());
        dto.setMemory(node.getStatus().getCapacity().get("memory").getAmount());

        dto.setPods(podsInNode.stream()
            .map(PodMapper::toDTO)
            .toList());

        return dto;
    }
}

