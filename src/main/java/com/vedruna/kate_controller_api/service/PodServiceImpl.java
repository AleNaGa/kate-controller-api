package com.vedruna.kate_controller_api.service;


import com.vedruna.kate_controller_api.config.KubernetesClientFactory;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Service;

@Service
public class PodServiceImpl implements PodServiceI {


    /**
     * Escala un deployment en un namespace y con un nombre dado a 0 réplicas.
     * Básicamente "apaga" el deployment
     *
     * @param apiServerUrl URL del servidor de API de kubernetes
     * @param token Token de autenticación
     * @param namespace Namespace en el que se encuentra el deployment
     * @param deploymentName Nombre del deployment a escalar
     */
    @Override
    public void scaleDeploymentToZero(String apiServerUrl, String token, String namespace, String deploymentName) {
         try (KubernetesClient client = KubernetesClientFactory.getClient(apiServerUrl, token)) {
            Deployment deployment = client.apps().deployments()
                    .inNamespace(namespace)
                    .withName(deploymentName)
                    .scale(0); // Escala a 0 réplicas
        }
    }
    /**
     * Escala un deployment en un namespace y con un nombre dado a cierto número de réplicas.
     *
     * @param apiServerUrl URL del servidor de API de kubernetes
     * @param token Token de autenticación
     * @param namespace Namespace en el que se encuentra el deployment
     * @param deploymentName Nombre del deployment a escalar
     * @param replicas Número de réplicas a las que se quiere escalar el deployment
     */
    
    @Override
    public void scaleDeployment(String apiServerUrl, String token, String namespace, String deploymentName,
            int replicas) {
        try (KubernetesClient client = KubernetesClientFactory.getClient(apiServerUrl, token)) {
            Deployment deployment = client.apps().deployments()
                    .inNamespace(namespace)
                    .withName(deploymentName)
                    .scale(replicas); // Escala a x réplicas
        }
    }
    
}
