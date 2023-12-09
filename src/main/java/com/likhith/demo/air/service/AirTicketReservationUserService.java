package com.likhith.demo.air.service;

import org.springframework.stereotype.Service;

import com.likhith.demo.air.document.User;
import com.likhith.demo.air.dto.AirTicketReservationResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface AirTicketReservationUserService {

	Flux<AirTicketReservationResponse> getAllUsers();

	Mono<AirTicketReservationResponse> getUser(String id);

	Mono<AirTicketReservationResponse> createUser(User user);

	Mono<AirTicketReservationResponse> updateUser(User user);

	Mono<AirTicketReservationResponse> deleteUser(String id);

}