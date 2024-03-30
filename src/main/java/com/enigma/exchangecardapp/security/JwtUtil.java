package com.enigma.exchangecardapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enigma.exchangecardapp.model.entity.AppUser;
import com.enigma.exchangecardapp.model.entity.Customer;
import com.enigma.exchangecardapp.service.CustomerService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class JwtUtil {
    private final String jwtSecret = "secret";
    private final String appName = "Exchange Card App";
    private final CustomerService customerService;
    public JwtUtil(CustomerService customerService) {
        this.customerService = customerService;
    }

    public String generateToken(AppUser appUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            Optional<Customer> customer = customerService.getCustomerByUserId(appUser.getId());
            String customerId = customer.map(Customer::getId).orElse(null);
            String token = JWT.create()
                    .withIssuer(appName)
                    .withSubject(appUser.getId())
                    .withClaim("customerId", customerId)
                    .withExpiresAt(Instant.now().plusSeconds(3600))
                    .withClaim("role", appUser.getRoles().stream().map(Enum::name).toList())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException();
        }
    }

    public boolean verifyJwtToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getIssuer().equals(appName);
        } catch (JWTCreationException e) {
            throw new RuntimeException();
        }
    }

    public Map<String, String> getUserInfoByToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("userId", decodedJWT.getSubject());
            userInfo.put("customerId", decodedJWT.getClaim("customerId").asString());
            return userInfo;
        } catch (JWTCreationException e) {
            throw new RuntimeException();
        }
    }
}
