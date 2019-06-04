package com.vending.machine;

import com.vending.machine.controller.VendingMachineController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = VendingMachineController.class)
public class VendingApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(VendingApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(VendingApplication.class);
	}

}
