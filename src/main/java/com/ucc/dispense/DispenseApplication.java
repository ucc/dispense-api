package com.ucc.dispense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DispenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DispenseApplication.class, args);
	}

}
