package com.issuer.issuerms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class IssuermsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssuermsApplication.class, args);
	}

}
