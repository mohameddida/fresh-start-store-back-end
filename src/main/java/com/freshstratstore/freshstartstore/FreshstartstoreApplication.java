package com.freshstratstore.freshstartstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FreshstartstoreApplication {
	@GetMapping("/hello")
	public String getMessage() {
		return "hello finaly";
	}

	public static void main(String[] args) {
		SpringApplication.run(FreshstartstoreApplication.class, args);
	}

}
