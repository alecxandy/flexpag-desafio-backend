package com.flexpag.paymentscheduler.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTValidateFilter extends BasicAuthenticationFilter {
    public static final String HEADER_ATTRIBUTIVE = "authorization";
    public static final String ATTRIBUTIVE_PREFIX = "Bearer ";

    public JWTValidateFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        String attributive = request.getHeader(HEADER_ATTRIBUTIVE);

        if (attributive == null) {
            chain.doFilter(request, response);
            return;
        }
        if (!attributive.startsWith(ATTRIBUTIVE_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = attributive.replace(ATTRIBUTIVE_PREFIX, "");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        String usuario = JWT.require(Algorithm.HMAC512(JWTAuthenticateFilter.TOKEN_PASSWORD))
                .build()
                .verify(token)
                .getSubject();

        if (usuario == null) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<>());
    }
}
