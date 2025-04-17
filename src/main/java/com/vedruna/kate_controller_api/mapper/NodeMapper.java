package com.vedruna.kate_controller_api.mapper;

import java.util.List;

import com.vedruna.kate_controller_api.dto.NodeDTO;

import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeAddress;
import io.fabric8.kubernetes.api.model.NodeCondition;
import io.fabric8.kubernetes.api.model.Pod;

public class NodeMapper {
    public static NodeDTO toDTO(Node node, List<Pod> podsInNode) {
        NodeDTO dto = new NodeDTO();
        dto.setName(node.getMetadata().getName());
        dto.setStatus(node.getStatus().getConditions()
            .stream()
            .filter(c -> c.getType().equals("Ready"))
            .findFirst()
            .map(NodeCondition::getStatus)
            .orElse("Unknown"));

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

