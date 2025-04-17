package com.vedruna.kate_controller_api.dto;

import lombok.Data;

@Data
public class ContainerDTO {
    private String name;
    private String image;
    private boolean ready;

}

