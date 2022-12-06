package com.htecgroup.uber.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.htecgroup.uber.exception.jwt.InvalidAlgorithmException;
import com.htecgroup.uber.exception.jwt.InvalidSessionTokenException;
import com.htecgroup.uber.model.jwt.JwtObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Log4j2
@Component
public class JwtUtil {

    public static final String AUTH_HEADER = HttpHeaders.AUTHORIZATION;

    public static final String REFRESH_TOKEN_HEADER = "refresh-token";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String CLAIM_NAME = "roles";

    public static long ACCESS_TOKEN_EXPIRATION_MS;
    public static Algorithm ALGORITHM;

    private static String generate(
            JwtObject jwtObject, long msUntilExpiration, String claimName, List<String> claims) {
        return JWT.create()
                .withSubject(jwtObject.getEmail())
                .withPayload(Map.of("uuid", jwtObject.getId().toString()))
                .withExpiresAt(new Date(System.currentTimeMillis() + msUntilExpiration))
                .withClaim(claimName, claims)
                .sign(ALGORITHM);
    }

    public static UsernamePasswordAuthenticationToken getFrom(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(ALGORITHM).build().verify(token);

            String username = decodedJWT.getSubject();
            String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
            Collection<SimpleGrantedAuthority> authorities =
                    stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        } catch (JWTVerificationException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            throw new InvalidSessionTokenException();
        } catch (IllegalArgumentException e) {
            log.error("JWT algorithm is null: {}", e.getMessage());
            throw new InvalidAlgorithmException();
        }
    }

    public static Collection<SimpleGrantedAuthority> getAuthoritiesFromRefreshToken(String token) {
        DecodedJWT decodedJWT = JWT.require(ALGORITHM).build().verify(token);
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities =
                stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    public static long stillValidForInMs(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(ALGORITHM).build().verify(token);
            Date expiresAt = decodedJWT.getExpiresAt();
            Date now = new Date(System.currentTimeMillis());
            return expiresAt.getTime() - now.getTime();
        } catch (JWTVerificationException e) {
            return 0;
        } catch (IllegalArgumentException e) {
            log.error("JWT algorithm is null: {}", e.getMessage());
            throw new InvalidAlgorithmException();
        }
    }

    public static String generateAccessToken(JwtObject jwtObject, List<String> claims) {
        return generate(jwtObject, ACCESS_TOKEN_EXPIRATION_MS, CLAIM_NAME, claims);
    }

    @Value("${jwt.access-expiration-ms}")
    private void setAccessTokenExpirationMs(long expirationMs) {
        ACCESS_TOKEN_EXPIRATION_MS = expirationMs;
    }

    @Value("${jwt.secret}")
    private void setAlgorithm(String secret) {
        ALGORITHM = Algorithm.HMAC512(secret);
    }
}
