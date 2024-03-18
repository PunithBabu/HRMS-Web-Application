/**
 * JwtAuthenticationEntryPoint class implements the Spring Security AuthenticationEntryPoint interface. 
 * It handles the unauthorized access and returns a 401 Unauthorized response to the client.
 */
package com.hrms.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        // Constructor implementation
    }

    /**
     * Commence method is called when unauthenticated user tries to access a protected resource.
     * It sends a 401 Unauthorized response to the client.
     *
     * @param request       the HTTP request
     * @param response      the HTTP response
     * @param authException the AuthenticationException that occurred
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
