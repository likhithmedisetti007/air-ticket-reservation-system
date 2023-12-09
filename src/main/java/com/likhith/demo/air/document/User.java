package com.likhith.demo.air.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mongodb.lang.NonNull;

@Document(collection = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "user_id", "user_name", "gender", "country", "is_visa_available", "is_passport_available",
		"contact_number", "email", "message" })
public class User {

	@Id
	@JsonIgnore
	private String id;

	@Field("user_id")
	@JsonProperty("user_id")
	@NonNull
	private String userId;

	@Field("user_name")
	@JsonProperty("user_name")
	@NonNull
	private String userName;

	private String gender;

	private String country;

	@Field("is_visa_available")
	@JsonProperty("is_visa_available")
	private boolean visaAvailable;

	@Field("is_passport_available")
	@JsonProperty("is_passport_available")
	private boolean passportAvailable;

	@Field("contact_number")
	@JsonProperty("contact_number")
	private String contactNumber;

	private String email;

	@Transient
	private String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isVisaAvailable() {
		return visaAvailable;
	}

	public void setVisaAvailable(boolean visaAvailable) {
		this.visaAvailable = visaAvailable;
	}

	public boolean isPassportAvailable() {
		return passportAvailable;
	}

	public void setPassportAvailable(boolean passportAvailable) {
		this.passportAvailable = passportAvailable;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", userName=" + userName + ", gender=" + gender + ", country="
				+ country + ", visaAvailable=" + visaAvailable + ", passportAvailable=" + passportAvailable
				+ ", contactNumber=" + contactNumber + ", email=" + email + ", message=" + message + "]";
	}

}