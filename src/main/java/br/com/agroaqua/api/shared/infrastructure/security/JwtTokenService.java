package br.com.agroaqua.api.shared.infrastructure.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtTokenService {

    private final String secretKey;

    public JwtTokenService(@Value("${api.security.token.key}") String secretKey) {
        this.secretKey = secretKey;
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setIssuer("AgroAqua")
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities().iterator().next().getAuthority())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateToken(String token) {
        return Jwts.parserBuilder().
                setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Key getSignatureKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
