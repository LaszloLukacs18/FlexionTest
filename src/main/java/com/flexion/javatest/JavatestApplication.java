package com.flexion.javatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.flexionmobile.codingchallenge.integration.*;

@SpringBootApplication
public class JavatestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavatestApplication.class, args);
		FlexionIntegration flexionIntegration = new FlexionIntegration();
		IntegrationTestRunner integrationTestRunner = new IntegrationTestRunner();
		integrationTestRunner.runTests(flexionIntegration);
	}
}
