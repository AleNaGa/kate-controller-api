package com.vedruna.kate_controller_api.mapper;

import com.vedruna.kate_controller_api.dto.NamespaceDTO;

import io.fabric8.kubernetes.api.model.Namespace;

public class NamespaceMapper {
    public static NamespaceDTO toDTO(Namespace ns) {
        NamespaceDTO dto = new NamespaceDTO();
        dto.setName(ns.getMetadata().getName());
        return dto;
    }
}

