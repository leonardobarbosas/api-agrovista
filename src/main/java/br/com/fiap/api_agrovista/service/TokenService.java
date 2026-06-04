package br.com.fiap.api_agrovista.service;

import br.com.fiap.api_agrovista.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("fiap")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Erro na geração de token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("fiap")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            return null; // ← única mudança
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime
                .now()
                .plusDays(7)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}