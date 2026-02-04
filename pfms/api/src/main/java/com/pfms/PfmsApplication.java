package com.pfms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pfms"})
@EnableJpaRepositories(basePackages = "com.pfms")
@EnableCaching
public class PfmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfmsApplication.class, args);
		System.out.println("===================*******The PFMS project started**********================");
	}

}
