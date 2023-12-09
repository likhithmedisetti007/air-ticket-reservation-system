package com.likhith.demo.air.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.demo.air.dto.AirTicketReservationRequest;
import com.likhith.demo.air.dto.AirTicketReservationResponse;
import com.likhith.demo.air.service.AirTicketReservationUserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/airTicketReservation/user")
public class AirTicketReservationUserController {

	@Autowired
	AirTicketReservationUserService service;

	@GetMapping("/getAllUsers")
	public Flux<AirTicketReservationResponse> getAllUsers() {
		return service.getAllUsers();
	}

	@GetMapping("/getUser/{id}")
	public Mono<AirTicketReservationResponse> getUser(@PathVariable("id") String id) {
		return service.getUser(id);
	}

	@PostMapping("/createUser")
	public Mono<AirTicketReservationResponse> createUser(@RequestBody AirTicketReservationRequest request) {
		return service.createUser(request.getUser());
	}

	@PutMapping("/updateUser")
	public Mono<AirTicketReservationResponse> updateUser(@RequestBody AirTicketReservationRequest request) {
		return service.updateUser(request.getUser());
	}

	@DeleteMapping("/deleteUser/{id}")
	public Mono<AirTicketReservationResponse> deleteUser(@PathVariable("id") String id) {
		return service.deleteUser(id);
	}

}