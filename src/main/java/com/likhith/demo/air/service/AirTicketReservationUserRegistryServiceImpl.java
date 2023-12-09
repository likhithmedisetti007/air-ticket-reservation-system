package com.likhith.demo.air.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.likhith.demo.air.document.UserRegistry;
import com.likhith.demo.air.dto.AirTicketReservationResponse;
import com.likhith.demo.air.exception.ErrorMessage;
import com.likhith.demo.air.repository.UserRegistryRepository;

import reactor.core.publisher.Mono;

@Component
public class AirTicketReservationUserRegistryServiceImpl implements AirTicketReservationUserRegistryService {

	@Autowired
	UserRegistryRepository repository;

	@Override
	public Mono<AirTicketReservationResponse> registerUser(UserRegistry userRegistry) {
		return repository
				.findByIdentityTypeAndIdentityProof(userRegistry.getIdentityType(), userRegistry.getIdentityProof())
				.flatMap(existingUserRegistry -> Mono.just(new AirTicketReservationResponse(
						new ErrorMessage(HttpStatus.CONFLICT.value(), "User already Registered"))))
				.switchIfEmpty(repository.save(userRegistry).map(userSavedRegistry -> {
					userSavedRegistry.setMessage("User has been successfully Registered");
					return new AirTicketReservationResponse(userSavedRegistry);
				})).onErrorResume(throwable -> Mono.just(new AirTicketReservationResponse(
						new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), "User Registration unsuccessful"))));
	}

	@Override
	public Mono<AirTicketReservationResponse> deRegisterUser(String registryId) {
		return repository.findById(registryId)
				.flatMap(existingUser -> repository.delete(existingUser).then(Mono.just(existingUser)))
				.map(deletedUser -> {
					deletedUser.setMessage("User has been Deregistered successfully");
					return new AirTicketReservationResponse(deletedUser);
				})
				.switchIfEmpty(Mono.just(new AirTicketReservationResponse(
						new ErrorMessage(HttpStatus.NOT_FOUND.value(), "No User found in Registry"))))
				.onErrorResume(throwable -> Mono.just(new AirTicketReservationResponse(new ErrorMessage(
						HttpStatus.INTERNAL_SERVER_ERROR.value(), "User Deregistration unsuccessful"))));
	}

	@Override
	public Mono<AirTicketReservationResponse> verifyUser(String registryId) {
		return repository.findById(registryId).flatMap(userRegistry -> {
			if (userRegistry.isVerified()) {
				userRegistry.setMessage("User has been already Verified");
				return Mono.just(new AirTicketReservationResponse(userRegistry));
			} else {
				userRegistry.setVerified(true);
				return repository.save(userRegistry).map(userSavedRegistry -> {
					userSavedRegistry.setMessage("User has been successfully Verified");
					return new AirTicketReservationResponse(userRegistry);
				});
			}
		}).switchIfEmpty(Mono.just(new AirTicketReservationResponse(new ErrorMessage(HttpStatus.NOT_FOUND.value(),
				"No User found in Registry. Please Register the User first and then Verify"))))
				.onErrorResume(throwable -> Mono.just(new AirTicketReservationResponse(
						new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), "User Verification unsuccessful"))));
	}

}