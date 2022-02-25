package com.decodedbytes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.decodedbytes.beans")
public class SimpleCamelSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCamelSpringbootApplication.class, args);
	}

}
