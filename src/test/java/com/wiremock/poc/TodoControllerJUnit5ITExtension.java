package com.wiremock.poc;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;

@ExtendWith(SpringExtension.class)
@WireMockTest
public class TodoControllerJUnit5ITExtension {

	 @Autowired
	  private WebTestClient webTestClient;

	  @Test
	  void basicWireMockExample() {

	    stubFor(
	      get("/todos")
	        .willReturn(ok()
	          .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
	          .withBodyFile("response-200.json"))
	    );

	    this.webTestClient
	      .get()
	      .uri("/api/todos")
	      .exchange()
	      .expectStatus().isOk()
	      .expectBody().jsonPath("$.length()").isEqualTo(3)
	      .jsonPath("$[0].title").isEqualTo("delectus aut autem");
	  }
	}
