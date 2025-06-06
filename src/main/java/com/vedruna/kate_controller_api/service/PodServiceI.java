package com.vedruna.kate_controller_api.service;

public interface PodServiceI {
    void scaleDeploymentToZero(String apiServerUrl, String token, String namespace, String deploymentName);

    void scaleDeployment(String apiServerUrl, String token, String namespace, String deploymentName, int replicas);

}