package com.likhith.demo.air.exception;

import java.util.Date;

public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int statusCode;
	private String errorMessage;
	private Date timestamp;

	public ValidationException(int statusCode, String errorMessage, Date timestamp) {
		super();
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.timestamp = timestamp;
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

}