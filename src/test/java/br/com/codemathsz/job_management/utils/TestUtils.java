package br.com.codemathsz.job_management.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

public class TestUtils {
    public static String objectToJson(Object obj){
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID idCompany, String secret){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        var expireIn = Instant.now().plus(Duration.ofHours(2));

        return JWT.create().withIssuer("javagas")
                .withExpiresAt(expireIn)
                .withSubject(idCompany.toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);
    }
}