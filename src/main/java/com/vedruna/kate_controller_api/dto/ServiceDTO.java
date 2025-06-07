package com.vedruna.kate_controller_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
@Schema(description = "Información de un servicio Kubernetes")
public class ServiceDTO {

    @Schema(description = "Nombre del servicio", example = "mi-servicio")
    private String name;

    @Schema(description = "Namespace donde se encuentra el servicio", example = "default")
    private String namespace;

    @Schema(description = "Tipo de servicio (ClusterIP, NodePort, LoadBalancer, etc.)", example = "ClusterIP")
    private String type;

    @Schema(description = "Dirección IP interna del cluster para el servicio", example = "10.0.0.1")
    private String clusterIP;

    @Schema(description = "Lista de puertos expuestos por el servicio")
    private List<PortDTO> ports;

    @Schema(description = "Selector de etiquetas que define a qué pods se dirige el servicio")
    private Map<String, String> selector;
}
