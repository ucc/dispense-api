package com.ucc.dispense.config;

import com.ucc.dispense.auth.UccUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final UccUserArgumentResolver uccUserArgumentResolver;

	public WebConfig(UccUserArgumentResolver uccUserArgumentResolver) {
		this.uccUserArgumentResolver = uccUserArgumentResolver;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(uccUserArgumentResolver);
	}

}
