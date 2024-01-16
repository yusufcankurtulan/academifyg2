package com.example.academify;

import com.example.academify.users.UserProfileController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = UserProfileController.class)
public class AcademifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademifyApplication.class, args);
	}

}
