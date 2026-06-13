package com.boxStudio.BoxStudio.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

public class TokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/api/login") || path.equals("/api/register") || path.equals("/api/register/manager")) {
            chain.doFilter(request, response);
        } else {
            String header = request.getHeader(AUTHORIZATION);
            if (header != null && header.startsWith("Bearer ")) {
                try {
                    String token = header.substring("Bearer ".length());
                    Algorithm algo = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier = JWT.require(algo).build();
                    DecodedJWT decoded = verifier.verify(token);

                    String username = decoded.getSubject();
                    String[] roles = decoded.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(r -> authorities.add(new SimpleGrantedAuthority(r)));

                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, authorities));
                    chain.doFilter(request, response);
                } catch (Exception e) {
                    response.setStatus(FORBIDDEN.value());
                    Map<String, String> err = new HashMap<>();
                    err.put("error_message", e.getMessage());
                    response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), err);
                }
            } else { chain.doFilter(request, response); }
        }
    }
}