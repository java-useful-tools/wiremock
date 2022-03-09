package com.wiremock.poc;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;

@WireMockTest(httpPort = 8092)
public class ExampleTestJUnit5Extension {

	private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
	
	@Test
	public void exampleTest() throws IOException, InterruptedException {
//		stubFor(get("/validate/prime-number?number=1")
//				.willReturn(ok()
//						.withHeader("Content-Type", "application/json")
//						.withBody("HOLA")));
//						.withBody("{\"data\":\"some data\"}")));

		HttpResponse<?> response = makeCallTo("http://localhost:8092/some/thing");
//		HttpResponse<?> response = makeCallTo("http://localhost:8092/validate/prime-number?number=1");
		System.err.println("--------------------------------------");
		System.err.println("--------------------------------------");
		System.err.println("RES: " + response.body());
		System.err.println("--------------------------------------");
		System.err.println("--------------------------------------");
//		assertTrue("{\"data\":\"some data\"}".equals(response.body()));
//		assertTrue("HOLA".equals(response.body()));
		assertEquals("AF", response.body());
//		verify(getRequestedFor(urlPathEqualTo("/some/data")).withHeader("accept", equalTo("application/json")));
	}



	private HttpResponse<?> makeCallTo(String path) throws IOException, InterruptedException {
		 HttpRequest request = HttpRequest.newBuilder()
	                .GET()
	                .uri(URI.create(path))
//	                .uri(URI.create("http://localhost:8090/validate/prime-number?number=1"))
//	                .uri(URI.create("http://localhost:8092/validate/prime-number?number=1"))
	                .setHeader("User-Agent", "Java 11 HttpClient Bot")
//	                .setHeader("Content-Type", "application/json")
	                .build();

	        HttpResponse<?> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

	        // print response headers
//	        HttpHeaders headers = response.headers();
//	        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

	        // print status code
//	        System.out.println(response.statusCode());

	        // print response body
//	        System.out.println(response.body());

		return response;
	}

}