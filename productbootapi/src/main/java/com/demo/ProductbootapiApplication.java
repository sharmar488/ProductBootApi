package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProductbootapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductbootapiApplication.class, args);
	}

}
