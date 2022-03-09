package com.wiremock.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.node.ArrayNode;

@RestController
@RequestMapping("/api/todos")
public class DemoController {

	@Autowired
	private WebClient todoWebClient;

	@GetMapping
	public ArrayNode getAllTodos() {
		return todoWebClient
				.get()
				.uri("/todos")
				.header("X-Auth", "duke")
				.retrieve()
				.bodyToMono(ArrayNode.class)
				.block();
	}
}