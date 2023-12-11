package com.likhith.demo.air.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.demo.air.dto.AirTicketReservationRequest;
import com.likhith.demo.air.dto.AirTicketReservationResponse;
import com.likhith.demo.air.service.AirTicketReservationUserRegistryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/airTicketReservation/userRegistry")
public class AirTicketReservationUserRegistryController {

	@Autowired
	AirTicketReservationUserRegistryService service;

	@GetMapping("/getAllRegisteredUsers")
	public Flux<AirTicketReservationResponse> getAllRegisteredUsers(
			@RequestParam(name = "verified", defaultValue = "true") boolean verified,
			@RequestParam(name = "active", defaultValue = "true") boolean active) {
		return service.getAllRegisteredUsers(verified, active);
	}

	@PostMapping("/registerUser")
	public Mono<AirTicketReservationResponse> registerUser(@RequestBody AirTicketReservationRequest request) {
		return service.registerUser(request.getUserRegistry());
	}

	@DeleteMapping("/deRegisterUser/{registryId}")
	public Mono<AirTicketReservationResponse> deRegisterUser(@PathVariable("registryId") String registryId) {
		return service.deRegisterUser(registryId);
	}

	@PatchMapping("/verifyUser/{registryId}")
	public Mono<AirTicketReservationResponse> verifyUser(@PathVariable("registryId") String registryId) {
		return service.verifyUser(registryId);
	}

}