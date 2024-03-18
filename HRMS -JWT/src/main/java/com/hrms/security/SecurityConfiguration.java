package com.hrms.security;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Configuration class for Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Value("${jwt.secret}")
	private String secret;

	/**
	 * Creates a bean for the password encoder.
	 * 
	 * @return The password encoder.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Creates a bean for the JWT request filter.
	 * 
	 * @param jwtUtil                   The JWT utility.
	 * @param jwtAuthenticationEntryPoint The JWT authentication entry point.
	 * @param userDetailsService        The user details service.
	 * @return The JWT request filter.
	 */
	@Bean
	public JwtRequestFilter jwtRequestFilter(JwtUtil jwtUtil, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
			UserDetailsServiceImpl userDetailsService) {
		return new JwtRequestFilter(jwtUtil, jwtAuthenticationEntryPoint, userDetailsService);
	}

	/**
	 * Creates a bean for the JWT utility.
	 * 
	 * @return The JWT utility.
	 */
	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}

	/**
	 * Creates a bean for the JWT authentication entry point.
	 * 
	 * @param objectMapper The object mapper.
	 * @return The JWT authentication entry point.
	 */
	@Bean
	public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
		return new JwtAuthenticationEntryPoint(objectMapper);
	}

	/**
	 * Creates a bean for the DAO authentication provider.
	 * 
	 * @return The DAO authentication provider.
	 */
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	/**
	 * Configures the security filter chain.
	 * 
	 * @param http The HttpSecurity object.
	 * @return The security filter chain.
	 * @throws Exception If an error occurs during configuration.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.cors(cors -> cors.configurationSource(request -> {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(List.of("http://localhost:4200"));
				config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
				config.setAllowedHeaders(List.of("*"));
				config.setExposedHeaders(List.of("Authorization"));
				config.setAllowCredentials(true);
				return config;
			}))
			.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
				.requestMatchers(HttpMethod.POST, "/api/v1/credentials/**").permitAll()
				.requestMatchers(HttpMethod.OPTIONS, "/api/v1/employees/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/v1/insights/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers("/**").permitAll())
			.exceptionHandling(exceptionHandling -> exceptionHandling
				.authenticationEntryPoint(jwtAuthenticationEntryPoint(objectMapper()))
			)
			.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(
			jwtRequestFilter(jwtUtil(), jwtAuthenticationEntryPoint(objectMapper()), userDetailsService),
			UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	/**
	 * Creates an authentication manager.
	 * 
	 * @param objectPostProcessor The object post processor.
	 * @return The authentication manager.
	 * @throws Exception If an error occurs during configuration.
	 */
	@SuppressWarnings("removal")
	@Bean
	public AuthenticationManager authenticationManager(ObjectPostProcessor<Object> objectPostProcessor)
			throws Exception {
		final AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectPostProcessor);
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()).and()
				.authenticationProvider(daoAuthenticationProvider());
		return builder.build();
	}

	/**
	 * Creates an object mapper.
	 * 
	 * @return The object mapper.
	 */
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

}
