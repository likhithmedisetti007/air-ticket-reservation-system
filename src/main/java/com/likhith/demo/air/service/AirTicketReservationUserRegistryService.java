package com.likhith.demo.air.service;

import org.springframework.stereotype.Service;

import com.likhith.demo.air.document.UserRegistry;
import com.likhith.demo.air.dto.AirTicketReservationResponse;

import reactor.core.publisher.Mono;

@Service
public interface AirTicketReservationUserRegistryService {

	Mono<AirTicketReservationResponse> registerUser(UserRegistry userRegistry);

	Mono<AirTicketReservationResponse> deRegisterUser(String registryId);

	Mono<AirTicketReservationResponse> verifyUser(String registryId);

}