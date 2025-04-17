package com.vedruna.kate_controller_api.dto;

import java.util.List;

import lombok.Data;

@Data
public class NodeDTO {
    private String name;
    private String status;
    private String ip;
    private String cpu;
    private String memory;
    private List<PodRecordDTO> pods;

}

