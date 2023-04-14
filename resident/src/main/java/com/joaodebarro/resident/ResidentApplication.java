package com.joaodebarro.resident;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;


@SpringBootApplication(
		scanBasePackages = {
				"com.carnegieworks.configuration",
				"com.carnegieworks.exceptionHandler",
				"com.joaodebarro.resident"
		}
)

public class ResidentApplication {

	public static void main(String[] args) {
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(ResidentApplication.class, args);
	}

}
