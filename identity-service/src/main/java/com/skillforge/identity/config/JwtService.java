package com.skillforge.identity.config;

import com.skillforge.identity.entity.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  private final SecretKey key;
  private final long expirationMs;

  public JwtService(
      @Value("${skillforge.jwt.secret}") String secret,
      @Value("${skillforge.jwt.expiration-ms}") long expirationMs) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    this.expirationMs = expirationMs;
  }

  public String generateToken(String userId, UserRole role) {
    var now = System.currentTimeMillis();
    return Jwts.builder()
        .subject(userId)
        .claim("role", role.name())
        .issuedAt(new Date(now))
        .expiration(new Date(now + expirationMs))
        .signWith(key)
        .compact();
  }

  public boolean valid(String token) {
    try {
      Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public String extractUserId(String token) {
    return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
  }

  public UserRole extractRole(String token) {
    var claim =
        Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().get("role", String.class);
    return UserRole.valueOf(claim);
  }

  public List<org.springframework.security.core.authority.SimpleGrantedAuthority> authoritiesFromToken(
      String token) {
    var role = extractRole(token);
    return List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + role.name()));
  }
}
