package com.keepitup.magjobbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MagJobBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagJobBackendApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(@Value("${keepitup.magjob.backend.url}") String baseUrl) {
		return new RestTemplateBuilder().rootUri(baseUrl).build();
	}

}
