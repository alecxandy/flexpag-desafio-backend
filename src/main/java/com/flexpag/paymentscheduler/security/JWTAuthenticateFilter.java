package com.flexpag.paymentscheduler.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexpag.paymentscheduler.domain.User;
import com.flexpag.paymentscheduler.domain.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticateFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRATION = 600_000;
    public static final String TOKEN_PASSWORD = "2c6abea7-6c59-44bf-897f-cfcd87594cb6";

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticateFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        try {
            //transformar json de usuario em uma classe
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUserName(),
                    user.getPassword(),
                    new ArrayList<>()
            ));

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage() + " failed to authenticate user");
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(userDetails.getUsername())//nome do usuario
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))//tempo de expiração
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));//assinatura do token
        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
