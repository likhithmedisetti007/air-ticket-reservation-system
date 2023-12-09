package com.likhith.demo.air.dto;

import com.likhith.demo.air.document.User;
import com.likhith.demo.air.document.UserRegistry;

public class AirTicketReservationRequest {

	private User user;
	private UserRegistry userRegistry;

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

	@Override
	public String toString() {
		return "AirTicketReservationRequest [user=" + user + ", userRegistry=" + userRegistry + "]";
	}

}