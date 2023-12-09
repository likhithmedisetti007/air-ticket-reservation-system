package com.likhith.demo.air.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.likhith.demo.air.document.User;
import com.likhith.demo.air.document.UserRegistry;
import com.likhith.demo.air.exception.ErrorMessage;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirTicketReservationResponse {

	private User user;
	private UserRegistry userRegistry;
	private ErrorMessage error;

	public AirTicketReservationResponse(User user) {
		super();
		this.user = user;
	}

	public AirTicketReservationResponse(UserRegistry userRegistry) {
		super();
		this.userRegistry = userRegistry;
	}

	public AirTicketReservationResponse(ErrorMessage error) {
		super();
		this.error = error;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserRegistry getUserRegistry() {
		return userRegistry;
	}

	public void setUserRegistry(UserRegistry userRegistry) {
		this.userRegistry = userRegistry;
	}

	public ErrorMessage getError() {
		return error;
	}

	public void setError(ErrorMessage error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "AirTicketReservationResponse [user=" + user + ", userRegistry=" + userRegistry + ", error=" + error
				+ "]";
	}

}