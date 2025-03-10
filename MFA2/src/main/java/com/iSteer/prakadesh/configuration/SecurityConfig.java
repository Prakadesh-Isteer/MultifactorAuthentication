package com.iSteer.prakadesh.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	UserDetailsService userDetailService;
	
	@Autowired
	JwtFilter jwtFilter;
	
@Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
	
	return http
			.csrf(csrf ->csrf.disable())
			.authorizeHttpRequests(request -> 
			request.requestMatchers("appsteer/register").permitAll()
			.anyRequest().authenticated()).sessionManagement(session -> session
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();	
}

@Bean
    AuthenticationProvider authenticationProvider() {
	
	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	provider.setPasswordEncoder(new BCryptPasswordEncoder(7));
	provider.setUserDetailsService(userDetailService);
	return provider;
}

@Bean
 AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Throwable {
	return configuration.getAuthenticationManager();
}

}
