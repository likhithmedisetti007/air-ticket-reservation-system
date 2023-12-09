package com.likhith.demo.air.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mongodb.lang.NonNull;

@Document(collection = "user_registry")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "user_name", "identity_type", "identity_proof", "is_verified", "is_active", "message" })
public class UserRegistry {

	@Id
	private String id;

	@Field("user_name")
	@JsonProperty("user_name")
	@NonNull
	private String userName;

	@Field("identity_type")
	@JsonProperty("identity_type")
	@NonNull
	private String identityType;

	@Field("identity_proof")
	@JsonProperty("identity_proof")
	@NonNull
	private String identityProof;

	@Field("is_verified")
	@JsonProperty("is_verified")
	private boolean verified = false;

	@Field("is_active")
	@JsonProperty("is_active")
	private boolean active = true;

	@Transient
	private String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentityProof() {
		return identityProof;
	}

	public void setIdentityProof(String identityProof) {
		this.identityProof = identityProof;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "UserRegistry [id=" + id + ", userName=" + userName + ", identityType=" + identityType
				+ ", identityProof=" + identityProof + ", verified=" + verified + ", active=" + active + ", message="
				+ message + "]";
	}

}