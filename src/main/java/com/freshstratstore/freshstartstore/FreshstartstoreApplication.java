package com.freshstratstore.freshstartstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FreshstartstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreshstartstoreApplication.class, args);
	}

}
