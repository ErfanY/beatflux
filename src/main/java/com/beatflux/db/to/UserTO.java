package com.beatflux.db.to;

import java.sql.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

public class UserTO {
   private int userID;
   private String userName;
   private String firstName;
   private String lastName;
   private String password;
   private String passwordSalt;
   private String countryCode;
   private Date birthDate;
   private String email;
   private String mobileNumber;
   private Timestamp signupTimstamp;
   private Timestamp lastOnline;
   private double latitude;
   private double longitude;
   
   public int getUserID() {
      return userID;
   }
   public void setUserID(int userID) {
      this.userID = userID;
   }
   public String getUserName() {
      return userName;
   }
   public void setUserName(String userName) {
      this.userName = userName;
   }
   public String getFirstName() {
      return firstName;
   }
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }
   public String getLastName() {
      return lastName;
   }
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public String getPasswordSalt() {
      return passwordSalt;
   }
   public void setPasswordSalt(String passwordSalt) {
      this.passwordSalt = passwordSalt;
   }
   public String getCountryCode() {
      return countryCode;
   }
   public void setCountryCode(String countryCode) {
      this.countryCode = countryCode;
   }
   public Date getBirthDate() {
      return birthDate;
   }
   public void setBirthDate(Date birthDate) {
      this.birthDate = birthDate;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getMobileNumber() {
      return mobileNumber;
   }
   public void setMobileNumber(String mobileNumber) {
      this.mobileNumber = mobileNumber;
   }
   public Timestamp getSignupTimstamp() {
      return signupTimstamp;
   }
   public void setSignupTimstamp(Timestamp signupTimstamp) {
      this.signupTimstamp = signupTimstamp;
   }
   public Timestamp getLastOnline() {
      return lastOnline;
   }
   public void setLastOnline(Timestamp lastOnline) {
      this.lastOnline = lastOnline;
   }
   public double getLatitude() {
      return latitude;
   }
   public void setLatitude(double latitude) {
      this.latitude = latitude;
   }
   public double getLongitude() {
      return longitude;
   }
   public void setLongitude(double longitude) {
      this.longitude = longitude;
   }
}
