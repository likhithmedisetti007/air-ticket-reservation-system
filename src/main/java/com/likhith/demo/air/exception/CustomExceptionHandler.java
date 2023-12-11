package com.likhith.demo.air.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.likhith.demo.air.dto.AirTicketReservationResponse;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(TechnicalException.class)
	public ResponseEntity<AirTicketReservationResponse> technicalException(TechnicalException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getStatusCode(), exception.getErrorMessage());
		return ResponseEntity.status(errorMessage.getStatusCode()).body(new AirTicketReservationResponse(errorMessage));
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<AirTicketReservationResponse> validationException(ValidationException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getStatusCode(), exception.getErrorMessage());
		return ResponseEntity.status(errorMessage.getStatusCode()).body(new AirTicketReservationResponse(errorMessage));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<AirTicketReservationResponse> runtimeException(RuntimeException exception) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Unexpected Error occured. Please try again after sometime");
		return ResponseEntity.status(errorMessage.getStatusCode()).body(new AirTicketReservationResponse(errorMessage));
	}
}