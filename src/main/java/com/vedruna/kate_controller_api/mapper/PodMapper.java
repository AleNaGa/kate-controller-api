package com.vedruna.kate_controller_api.mapper;



import com.vedruna.kate_controller_api.dto.PodRecordDTO;

import java.util.ArrayList;
import java.util.List;

import com.vedruna.kate_controller_api.dto.ContainerDTO;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.Pod;

public class PodMapper {
    public static PodRecordDTO toDTO(Pod pod) {
        PodRecordDTO dto = new PodRecordDTO();
        dto.setName(pod.getMetadata().getName());
        dto.setNamespace(pod.getMetadata().getNamespace());
        dto.setStatus(pod.getStatus().getPhase());
        dto.setStartTime(pod.getStatus().getStartTime());

        List<Container> containers = pod.getSpec().getContainers();
        List<ContainerStatus> statuses = pod.getStatus().getContainerStatuses();

        List<ContainerDTO> containerDTOs = new ArrayList<>();
        for (Container container : containers) {
            ContainerDTO containerDTO = ContainerMapper.toDTO(container);
            if (statuses != null) {
                statuses.stream()
                    .filter(status -> status.getName().equals(container.getName()))
                    .findFirst()
                    .ifPresent(status -> containerDTO.setReady(status.getReady()));
            }
            containerDTOs.add(containerDTO);
        }

        dto.setContainers(containerDTOs);
        return dto;
    }
}

