package com.vedruna.kate_controller_api.session;
import org.springframework.stereotype.Component;

import com.vedruna.kate_controller_api.config.KubernetesAuthRequest;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ClusterSessionManager {

    // Clase interna para almacenar los datos de la sesión
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

    // Sesion 
    public String createSession(KubernetesAuthRequest authRequest) {
        String sessionId = UUID.randomUUID().toString();
        sessions.put(sessionId, new SessionData(authRequest));
        return sessionId;
    }

    // Validación
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
