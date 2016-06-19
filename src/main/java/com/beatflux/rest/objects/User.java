package com.beatflux.rest.objects;

import java.sql.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private int userID;
	private String userName;
	private String firstName;
	private String lastName;
	private String countryCode;
	private Date birthDate;
	private String email;
	private String mobileNumber;
	private Timestamp signupTimstamp;
	private Timestamp lastOnline;
	private double latitude;
	private double longitude;
	
	@XmlAttribute
	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	@XmlAttribute
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@XmlAttribute
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@XmlAttribute
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlAttribute
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	@XmlAttribute
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@XmlAttribute
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlAttribute
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@XmlAttribute
	public Timestamp getSignupTimstamp() {
		return signupTimstamp;
	}

	public void setSignupTimstamp(Timestamp signupTimstamp) {
		this.signupTimstamp = signupTimstamp;
	}

	@XmlAttribute
	public Timestamp getLastOnline() {
		return lastOnline;
	}

	public void setLastOnline(Timestamp lastOnline) {
		this.lastOnline = lastOnline;
	}

	@XmlAttribute
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@XmlAttribute
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
