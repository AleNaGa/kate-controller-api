package com.vedruna.kate_controller_api.dto;


import java.util.List;

import lombok.Data;


@Data
public class PodRecordDTO {
        private String name;
        private String namespace;
        private String status;
        private String startTime;
        private List<ContainerDTO> containers;
        private String deployment; // opcional
        private List<String> services; // opcional
    
  
    
}
