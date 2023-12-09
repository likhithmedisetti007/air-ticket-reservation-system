package com.likhith.demo.air.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.demo.air.dto.AirTicketReservationRequest;
import com.likhith.demo.air.dto.AirTicketReservationResponse;
import com.likhith.demo.air.service.AirTicketReservationUserRegistryService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/airTicketReservation/userRegistry")
public class AirTicketReservationUserRegistryController {

	@Autowired
	AirTicketReservationUserRegistryService service;

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