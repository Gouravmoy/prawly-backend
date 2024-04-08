package com.prawly.auth;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.prawly.exceptions.AccessDeniedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtTokenUtil {

	private static final int MINUTES = 60;

	static SecretKey key = Jwts.SIG.HS256.key().build();

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		var now = Instant.now();
		return Jwts.builder()
				.subject(subject)
				.issuedAt(Date.from(now))
				.expiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
				.signWith(key)
				.compact();
	}

	public static Boolean validateToken(String token, UserDetails userDetails) {
		String username;
		username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private static boolean isTokenExpired(String token) {
		Claims claims = getTokenBody(token);
		return claims.getExpiration().before(new Date());
	}

	public static String extractUsername(String token) {
			return getTokenBody(token).getSubject();
	}

	private static Claims getTokenBody(String token) {
		try {
			return Jwts
					.parser()
					.verifyWith(key)
					.build()
					.parseSignedClaims(token)
					.getPayload();
		} catch (SignatureException | ExpiredJwtException e) {
			throw new AccessDeniedException("Access denied: " + e.getMessage());
		}
	}

}
