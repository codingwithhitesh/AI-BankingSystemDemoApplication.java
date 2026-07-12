package com.hitesh.BankingSystemDemoAI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hitesh.BankingSystemDemo")
public class BankingSystemDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemDemoApplication.class, args);
	}

}
