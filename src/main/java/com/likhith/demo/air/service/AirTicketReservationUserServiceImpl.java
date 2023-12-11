package com.likhith.demo.air.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.likhith.demo.air.document.User;
import com.likhith.demo.air.dto.AirTicketReservationResponse;
import com.likhith.demo.air.exception.TechnicalException;
import com.likhith.demo.air.exception.ValidationException;
import com.likhith.demo.air.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AirTicketReservationUserServiceImpl implements AirTicketReservationUserService {

	@Autowired
	UserRepository repository;

	@Override
	public Flux<AirTicketReservationResponse> getAllUsers() {
		return repository.findAll().flatMap(allUsers -> {
			return Flux.just(new AirTicketReservationResponse(allUsers));
		}).switchIfEmpty(Flux.error(new ValidationException(HttpStatus.NOT_FOUND.value(), "No Users found")))
				.onErrorMap(this::throwException);
	}

	@Override
	public Mono<AirTicketReservationResponse> getUser(String id) {
		return repository.findByUserId(id).flatMap(user -> Mono.just(new AirTicketReservationResponse(user)))
				.switchIfEmpty(Mono.error(new ValidationException(HttpStatus.NOT_FOUND.value(), "No User found")))
				.onErrorMap(this::throwException);
	}

	@Override
	public Mono<AirTicketReservationResponse> createUser(User user) {
		return repository.findByUserId(user.getUserId())
				.flatMap(existingUser -> Mono
						.error(new ValidationException(HttpStatus.CONFLICT.value(), "User already exists")))
				.switchIfEmpty(repository.save(user).map(savedUser -> {
					savedUser.setMessage("User has been successfully Created");
					return new AirTicketReservationResponse(savedUser);
				})).onErrorMap(this::throwException).cast(AirTicketReservationResponse.class);
	}

	@Override
	public Mono<AirTicketReservationResponse> updateUser(User user) {
		return repository.findByUserId(user.getUserId()).flatMap(existingUser -> {
			user.setId(existingUser.getId());
			user.setMessage("User has been successfully Updated");
			return repository.save(user).thenReturn(new AirTicketReservationResponse(user));
		}).switchIfEmpty(Mono.error(new ValidationException(HttpStatus.NOT_FOUND.value(), "No User found")))
				.onErrorMap(this::throwException);
	}

	@Override
	public Mono<AirTicketReservationResponse> deleteUser(String id) {
		return repository.findByUserId(id)
				.flatMap(existingUser -> repository.delete(existingUser).then(Mono.just(existingUser)))
				.map(deletedUser -> {
					deletedUser.setMessage("User has been Deleted successfully");
					return new AirTicketReservationResponse(deletedUser);
				}).switchIfEmpty(Mono.error(new ValidationException(HttpStatus.NOT_FOUND.value(), "User not found")))
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