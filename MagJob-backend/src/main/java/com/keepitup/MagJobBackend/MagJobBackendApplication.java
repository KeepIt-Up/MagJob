package com.keepitup.MagJobBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class MagJobBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagJobBackendApplication.class, args);
	}

}
