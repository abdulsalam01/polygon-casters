/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.services.ext;

import com.example.constants.Credentials;
import com.example.constants.Defaults;
import com.example.constants.Endpoints;
import com.example.models.AuthManager;
import com.example.models.SessionManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author abdulsalam
 */
@Service
public class Auth extends Base {

    private static Auth instance;

    public Auth() {
        super();
    }

    public static Auth getInstance() {
        if (Auth.instance == null) {
            Auth.instance = new Auth();
        }

        return Auth.instance;
    }

    private static String prepareStatements() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> payload = new HashMap<>();

        payload.put(Defaults.USERNAME, Credentials.USERNAME);
        payload.put(Defaults.PASSWORD, Credentials.PASSWORD);

        try {
            String jsonBody = mapper.writeValueAsString(payload);
            return jsonBody;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(AuthManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Defaults.STRINGS;
    }

    public static CompletableFuture<AuthManager> Do() {
        String url = Endpoints.LOGIN;
        String jsonBody = Auth.prepareStatements();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return Auth.httpClient.sendAsync(request,
                HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(response -> {
                    // Create an ObjectMapper instance.
                    ObjectMapper objectMapper = new ObjectMapper();
                    // Map the JSON response to the AuthResponse class.
                    AuthManager authResponse = null;

                    try {
                        authResponse = objectMapper.readValue(response, AuthManager.class);
                    } catch (JsonProcessingException ex) {
                        Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Session management.
                    SessionManager.keepSession(authResponse);
                    return SessionManager.getCurrentSession();
                });

    }

    public AuthManager Current() {
        AuthManager m = null;

        try {
            m = SessionManager.getCurrentSession() == null
                    ? Auth.Do().get()
                    : SessionManager.getCurrentSession();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return m;
    }
}
