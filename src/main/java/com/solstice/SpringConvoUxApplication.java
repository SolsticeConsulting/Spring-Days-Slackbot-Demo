package com.solstice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"me.ramswaroop.jbot", "com.solstice"})
public class SpringConvoUxApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringConvoUxApplication.class, args);
	}
}
