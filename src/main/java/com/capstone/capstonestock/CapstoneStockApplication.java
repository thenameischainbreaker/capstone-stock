package com.capstone.capstonestock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CapstoneStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneStockApplication.class, args);
	}

}
