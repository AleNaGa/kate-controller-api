package com.vedruna.kate_controller_api.mapper;



import com.vedruna.kate_controller_api.dto.PodRecordDTO;

import java.util.ArrayList;
import java.util.List;

import com.vedruna.kate_controller_api.dto.ContainerDTO;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.Pod;

public class PodMapper {
    /**
     * Convierte un objeto Pod de Kubernetes en un objeto PodRecordDTO, que es el que se devuelve en la API de Kate.
     * Extrae la información del Pod y sus contenedores, y establece el estado de readiness para cada contenedor.
     * Convierte los contenedores en un objeto ContainerDTO y los agrega a la lista de contenedores en el PodRecordDTO.
     * 
     * @param pod El objeto Pod de Kubernetes a convertir.
     * @return Un objeto PodRecordDTO con la información del Pod y sus contenedores.
     */

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

