/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.models;

/**
 *
 * @author abdulsalam
 */
public class SessionManager {

    private static AuthManager currentSession;

    // Session method to store the session data.
    public static AuthManager keepSession(AuthManager model) {
        SessionManager.currentSession = model;
        return SessionManager.currentSession;
    }

    public static AuthManager getCurrentSession() {
        return SessionManager.currentSession;
    }

    public static void clearSession() {
        SessionManager.currentSession = null;
    }
}
