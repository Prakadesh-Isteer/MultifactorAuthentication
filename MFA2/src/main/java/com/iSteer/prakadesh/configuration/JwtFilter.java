package com.iSteer.prakadesh.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.iSteer.prakadesh.repository.UserDataRepo;
import com.iSteer.prakadesh.service.JwtFilterUtil;
import com.iSteer.prakadesh.service.UserDataService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtFilterUtil util;
	
	@Autowired
	UserDataRepo repo;
	
	@Autowired
	UserDataService service;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String userName = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			userName = util.extractUserName(token);
		}
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDeatils = service.loadUserByUsername(userName);
			try {
			if(util.validateToken(token, userDeatils)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDeatils, null, userDeatils.getAuthorities());			
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
//				System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
				System.out.println("mfa");
				}
			}catch(ExpiredJwtException e) {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
	            response.getWriter().write("JWT Token has expired.");
	            return;
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
}
