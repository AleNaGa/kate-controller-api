package com.vedruna.kate_controller_api.session;
import org.springframework.stereotype.Component;

import com.vedruna.kate_controller_api.config.KubernetesAuthRequest;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ClusterSessionManager {


    // Datos de la sesión
    private static class SessionData {
        KubernetesAuthRequest authRequest;
        Instant createdAt;
        SessionData(KubernetesAuthRequest authRequest) {
            this.authRequest = authRequest;
            this.createdAt = Instant.now();
        }
    }

    private final Map<String, SessionData> sessions = new ConcurrentHashMap<>();
    private static final long SESSION_TTL_SECONDS = 3600; // 1h


    /**
     * Crea una sesión y devuelve su id. La sesión tendrá el ttl definido en SESSION_TTL_SECONDS.
     * @param authRequest Información de autenticación para la sesión
     * @return id de la sesión
     */
    public String createSession(KubernetesAuthRequest authRequest) {
        String sessionId = UUID.randomUUID().toString();
        sessions.put(sessionId, new SessionData(authRequest));
        return sessionId;
    }

    /**
     * Obtiene la información de autenticación asociada a la sesión con el id dado.
     * Si la sesión no existe o ha expirado, devuelve null.
     * 
     * @param sessionId id de la sesión
     * @return Información de autenticación asociada a la sesión
     */
    public KubernetesAuthRequest getAuthRequest(String sessionId) {
        SessionData session = sessions.get(sessionId);
        if (session == null) return null;

        // Expired?
        if (Instant.now().isAfter(session.createdAt.plusSeconds(SESSION_TTL_SECONDS))) {
            sessions.remove(sessionId);
            return null;
        }
        return session.authRequest;
    }
}
