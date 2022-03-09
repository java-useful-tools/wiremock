package com.wiremock.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.contract.stubrunner.server.EnableStubRunnerServer;

@SpringBootApplication
@EnableStubRunnerServer
public class WiremockPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiremockPocApplication.class, args);
	}

}
