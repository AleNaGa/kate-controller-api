package com.vedruna.kate_controller_api.mapper;

import com.vedruna.kate_controller_api.dto.NamespaceDTO;

import io.fabric8.kubernetes.api.model.Namespace;

public class NamespaceMapper {
    /**
     * Convierte un objeto Namespace de Kubernetes en un objeto NamespaceDTO, que es el que se devuelve
     * en la API de Kate.
     * @param ns el objeto Namespace que se va a convertir
     * @return el objeto NamespaceDTO que se ha creado
     */
    public static NamespaceDTO toDTO(Namespace ns) {
        NamespaceDTO dto = new NamespaceDTO();
        dto.setName(ns.getMetadata().getName());
        return dto;
    }
}

