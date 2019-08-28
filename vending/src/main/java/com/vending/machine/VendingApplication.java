package com.vending.machine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.vending")
//@ComponentScan(basePackages = "com.vending.machine")
@EnableJpaRepositories(basePackages = "com.vending.machine", considerNestedRepositories = true)
public class VendingApplication extends SpringBootServletInitializer {
	public static void main(String... args) {
		SpringApplication.run(VendingApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(VendingApplication.class);
	}

}
