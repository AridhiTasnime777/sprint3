package com.chansons.chansons.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // CORS pre-flight requests
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        String jwt = request.getHeader("Authorization");
        System.out.println("--- Authorization Filter ---");
        System.out.println("URI: " + request.getRequestURI());
        System.out.println("Header: " + jwt);

        if (jwt == null || (!jwt.startsWith(SecParams.PREFIX) && !jwt.startsWith("Bearer "))) {
            System.out.println("No valid JWT header found.");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Remove prefix
            if (jwt.startsWith(SecParams.PREFIX)) {
                jwt = jwt.substring(SecParams.PREFIX.length());
            } else if (jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
            }

            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecParams.SECRET)).build();
            DecodedJWT decodedJWT = verifier.verify(jwt);
            String username = decodedJWT.getSubject();
            System.out.println("Authenticated User: " + username);
            
            List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);

            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for (String r : roles)
                authorities.add(new SimpleGrantedAuthority(r));

            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(user);
            
        } catch (Exception e) {
            System.err.println("JWT Verification Failed: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
