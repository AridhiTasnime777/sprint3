package com.chansons.chansons.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.chansons.chansons.entities.User;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        if (!request.getMethod().equals("POST")) {
            return null; // Or throw an exception
        }
        
        System.out.println("--- Login Request Received ---");
        System.out.println("Method: " + request.getMethod());
        System.out.println("Content-Type: " + request.getContentType());
        System.out.println("Content-Length: " + request.getContentLength());

        User user = null;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            if (user != null) {
                System.out.println("Login attempt for user: " + user.getUsername());
            }
        } catch (Exception e) {
            System.err.println("Error reading login request: " + e.getMessage());
            throw new RuntimeException("Body is empty or invalid JSON. Content-Type: " + request.getContentType());
        }

        if (user == null) {
            throw new RuntimeException("User object is null after parsing JSON");
        }

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        System.err.println("!!! AUTHENTICATION FAILED !!!");
        System.err.println("URI: " + request.getRequestURI());
        System.err.println("Method: " + request.getMethod());
        System.err.println("Reason: " + failed.getMessage());
        super.unsuccessfulAuthentication(request, response, failed);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) authResult
                .getPrincipal();

        List<String> roles = new ArrayList<>();
        springUser.getAuthorities().forEach(au -> {
            roles.add(au.getAuthority());
        });

        String jwt = JWT.create().withSubject(springUser.getUsername())
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis() + SecParams.EXP_TIME))
                .sign(Algorithm.HMAC256(SecParams.SECRET));

        System.out.println("Login successful. Setting Authorization header: " + SecParams.PREFIX + jwt);
        
        // Brute force CORS headers for the login response specifically
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        
        response.addHeader("Authorization", SecParams.PREFIX + jwt);
        
        // Also return as JSON body
        response.setContentType("application/json");
        Map<String, Object> loginResponse = new java.util.HashMap<>();
        loginResponse.put("token", jwt);
        loginResponse.put("username", springUser.getUsername());
        loginResponse.put("roles", roles);
        
        String json = new ObjectMapper().writeValueAsString(loginResponse);
        response.getWriter().write(json);
        response.getWriter().flush();
    }
}
