package com.likhith.demo.air.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.likhith.demo.air.document.UserRegistry;
import com.likhith.demo.air.dto.AirTicketReservationResponse;
import com.likhith.demo.air.exception.TechnicalException;
import com.likhith.demo.air.exception.ValidationException;
import com.likhith.demo.air.repository.UserRegistryRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AirTicketReservationUserRegistryServiceImpl implements AirTicketReservationUserRegistryService {

	@Autowired
	UserRegistryRepository repository;

	@Override
	public Flux<AirTicketReservationResponse> getAllRegisteredUsers(boolean verified, boolean active) {
		if (verified || active) {
			return repository.findByVerifiedAndActive(verified, active)
					.flatMap(userRegistry -> Flux.just(new AirTicketReservationResponse(userRegistry)))
					.switchIfEmpty(Flux.error(new ValidationException(HttpStatus.NOT_FOUND.value(),
							verified && active ? "No Verified/Active Users found in Registry"
									: "No Users found with matching inputs in Registry")))
					.onErrorMap(this::throwException);
		} else {
			return repository.findAll()
					.flatMap(userRegistry -> Flux.just(new AirTicketReservationResponse(userRegistry)))
					.switchIfEmpty(Flux
							.error(new ValidationException(HttpStatus.NOT_FOUND.value(), "No User found in Registry")))
					.onErrorMap(this::throwException);
		}
	}

	@Override
	public Mono<AirTicketReservationResponse> registerUser(UserRegistry userRegistry) {
		return repository
				.findByIdentityTypeAndIdentityProof(userRegistry.getIdentityType(), userRegistry.getIdentityProof())
				.flatMap(existingUserRegistry -> Mono
						.error(new ValidationException(HttpStatus.CONFLICT.value(), "User already Registered")))
				.switchIfEmpty(repository.save(userRegistry).map(userSavedRegistry -> {
					userSavedRegistry.setMessage("User has been successfully Registered");
					return new AirTicketReservationResponse(userSavedRegistry);
				})).onErrorMap(this::throwException).cast(AirTicketReservationResponse.class);
	}

	@Override
	public Mono<AirTicketReservationResponse> deRegisterUser(String registryId) {
		return repository.findById(registryId)
				.flatMap(existingUser -> repository.delete(existingUser).then(Mono.just(existingUser)))
				.map(deletedUser -> {
					deletedUser.setMessage("User has been Deregistered successfully");
					return new AirTicketReservationResponse(deletedUser);
				})
				.switchIfEmpty(
						Mono.error(new ValidationException(HttpStatus.NOT_FOUND.value(), "No User found in Registry")))
				.onErrorMap(this::throwException);
	}

	@Override
	public Mono<AirTicketReservationResponse> verifyUser(String registryId) {
		return repository.findById(registryId).flatMap(userRegistry -> {
			if (userRegistry.isVerified()) {
				return Mono.error(new ValidationException(HttpStatus.CONFLICT.value(), "User already Verified"));
			} else {
				userRegistry.setVerified(true);
				return repository.save(userRegistry).map(userSavedRegistry -> {
					userSavedRegistry.setMessage("User has been successfully Verified");
					return new AirTicketReservationResponse(userRegistry);
				});
			}
		}).switchIfEmpty(Mono.error(new ValidationException(HttpStatus.NOT_FOUND.value(),
				"No User found in Registry. Please Register the User first and then Verify")))
				.onErrorMap(this::throwException);
	}

	private Throwable throwException(Throwable throwable) {

		System.out.println(throwable);

		if (throwable instanceof ValidationException) {
			ValidationException exception = (ValidationException) throwable;
			return new ValidationException(exception.getStatusCode(), exception.getErrorMessage());
		} else if (throwable instanceof TechnicalException) {
			TechnicalException exception = (TechnicalException) throwable;
			return new TechnicalException(exception.getStatusCode(), exception.getErrorMessage());
		} else {
			return new RuntimeException();
		}

	}

}