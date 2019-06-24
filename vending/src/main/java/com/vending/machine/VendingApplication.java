package com.vending.machine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@ComponentScan(basePackages = "com.vending.machine")
public class VendingApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(VendingApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(VendingApplication.class);
	}

}
