package com.ucc.dispense.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth -> {
			// Allow all requests to health endpoints
			auth.requestMatchers("/actuator/health")
				.permitAll()
				// All actuator requests need to be from a Wheel member
				.requestMatchers("/actuator/**")
				.hasAuthority("WHEEL")
				// Everything needs a token from keycloak.
				.anyRequest()
				.authenticated();
		})
			.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter())))
			.csrf(csrf -> csrf.disable())
			.build();
	}

	/**
	 * Maps usergroups from keycloak into Spring Authorities.
	 */
	JwtAuthenticationConverter jwtAuthConverter() {
		var converter = new JwtAuthenticationConverter();
		converter.setJwtGrantedAuthoritiesConverter(jwt -> {
			List<String> groups = jwt.getClaimAsStringList("usergroups");
			if (groups == null) {
				return List.of();
			}

			return groups.stream().map(this::usergroupToAuthority).toList();
		});

		return converter;
	}

	private GrantedAuthority usergroupToAuthority(String usergroup) {
		return new SimpleGrantedAuthority(usergroup.toUpperCase());
	}

}
