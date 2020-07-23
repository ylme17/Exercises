package com.example.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthService {

	@GetMapping("/health")
	public void health()
	{
	}
}
