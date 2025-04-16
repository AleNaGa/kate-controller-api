package com.vedruna.kate_controller_api.dto;

import java.util.List;
import java.util.Map;

public class ServiceDTO {
    private String name;
    private String namespace;
    private String type;
    private String clusterIP;
    private List<PortDTO> ports;
    private Map<String, String> selector;
}