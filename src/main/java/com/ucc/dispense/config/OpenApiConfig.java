package com.ucc.dispense.config;

import com.ucc.dispense.auth.UccUser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import jakarta.annotation.PostConstruct;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@PostConstruct
	void configureSpringDoc() {
		// UccUser gets constructed and passed to all endpoints automatically.
		// The user won't need to pass it.
		SpringDocUtils.getConfig().addRequestWrapperToIgnore(UccUser.class);
	}

	@Bean
	OpenAPI openAPI() {
		var schemeName = "bearerAuth";
		return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(schemeName))
			.components(new Components().addSecuritySchemes(schemeName,
					new SecurityScheme().name(schemeName)
						.type(SecurityScheme.Type.HTTP)
						.scheme("bearer")
						.bearerFormat("JWT")));
	}

}
