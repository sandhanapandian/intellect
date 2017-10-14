package com.intellect.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "user", indexes = {
		@Index(name = "UNQ_USER_EMAIL", columnList = "email", unique = true)})
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name="fName", nullable = false)
	private String fName;

	@Column(name="lName", nullable = false)
	private String lName;

	@Column(name="email", nullable = false)
	private String email;

	@Column(name="pincode", nullable = false)
	private Integer pinCode;

	@Column(name="dob", nullable = false)
	private Date birthDate;
	
	private boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
