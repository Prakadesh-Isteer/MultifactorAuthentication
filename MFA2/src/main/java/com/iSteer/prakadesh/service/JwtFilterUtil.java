package com.iSteer.prakadesh.service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtFilterUtil {
	public static Logger logging = LogManager.getLogger(MultiFactorAuthenticationService.class);
	private String secretKey = "3e8d497c3f8f3f2dBb6c8a72ad881b08a6b3fc43e7a0d76abf3cd@c34889d906";
	public String generateToken(String userName , String role) {
		return Jwts.builder()
				.claim("role", role)
				.subject(userName)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration( new Date(System.currentTimeMillis() + 10 * 60 * 1000*1000))
				.signWith(getKey())
				.compact();
				
	}
	
	public SecretKey getKey() {    
		byte[] byteArray = secretKey.getBytes();
		return Keys.hmacShaKeyFor(byteArray);
		
	}
	
	public String extractUserName(String token) {
		
		return extractClaim(token, Claims::getSubject);
		
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
	
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		logging.info("in validate token...............");
		final String userName = extractUserName(token);
		
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
	
		return extarctExpiration(token).before(new Date());
	}

	private Date extarctExpiration(String token) {
		
		return extractClaim(token,Claims::getExpiration);
	}


}
