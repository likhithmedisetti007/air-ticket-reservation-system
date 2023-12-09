package com.likhith.demo.air.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "status_code", "error_message", "timestamp" })
public class ErrorMessage {

	@JsonProperty("status_code")
	private int statusCode;

	@JsonProperty("error_message")
	private String errorMessage;

	private Date timestamp;

	public ErrorMessage(int statusCode, String errorMessage) {
		super();
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.timestamp = new Date();
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ErrorMessage [statusCode=" + statusCode + ", errorMessage=" + errorMessage + ", timestamp=" + timestamp
				+ "]";
	}

}