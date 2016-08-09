package com.beatflux.db.to;


import java.sql.Timestamp;

public class SpotTO {
   private int spotID;
   private String userName;
   private String name;
   private String equipment;
   private String password;
   private String passwordSalt;
   private String countryCode;
   private String email;
   private String phoneNumber;
   private Timestamp signupTimstamp;
   private Timestamp lastOnline;
   private double latitude;
   private double longitude;
   
   public int getSpotID() {
      return spotID;
   }
   public void setSpotID(int userID) {
      this.spotID = userID;
   }
   public String getUserName() {
      return userName;
   }
   public void setUserName(String userName) {
      this.userName = userName;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
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
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getPhoneNumber() {
      return phoneNumber;
   }
   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
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
   public String getEquipment() {
      return equipment;
   }
   public void setEquipment(String equipment) {
      this.equipment = equipment;
   }
}
