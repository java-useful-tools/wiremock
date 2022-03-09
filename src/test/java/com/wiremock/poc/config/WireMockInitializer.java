package com.wiremock.poc.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

import java.util.Map;

public class WireMockInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {

		WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort());

		wireMockServer.start();

		//Spring events to stop server when test context will finish too
		applicationContext.addApplicationListener(applicationEvent -> {
			if (applicationEvent instanceof ContextClosedEvent) {
				wireMockServer.stop();
			}
		});

		// register wireMockServer as a spring bean
		applicationContext.getBeanFactory().registerSingleton("wireMockServer", wireMockServer);

		// replace base url with woremock define not from properties
		TestPropertyValues.of(Map.of("todo_base_url", wireMockServer.baseUrl())).applyTo(applicationContext);

	}
}