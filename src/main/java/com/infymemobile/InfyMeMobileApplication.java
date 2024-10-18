package com.infymemobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.infymemobile.entity"})
@PropertySource(value = {"classpath:messages.properties"})
public class InfyMeMobileApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfyMeMobileApplication.class, args);
	}

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT");
	}

}
