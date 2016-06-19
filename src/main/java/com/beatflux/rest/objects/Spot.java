package com.beatflux.rest.objects;

import java.sql.Timestamp;

public class Spot {
   private int spotID;
   private String userName;
   private String name;
   private String equipement;
   private String password;
   private String passwordSalt;
   private String countryCode;
   private String email;
   private String phoneNumber;
   private Timestamp signupTimstamp;
   private Timestamp lastOnline;
   private double latitude;
   private double longitude;
   /**
    * @return the spotID
    */
   public int getSpotID() {
      return spotID;
   }
   /**
    * @param spotID the spotID to set
    */
   public void setSpotID(int spotID) {
      this.spotID = spotID;
   }
   /**
    * @return the userName
    */
   public String getUserName() {
      return userName;
   }
   /**
    * @param userName the userName to set
    */
   public void setUserName(String userName) {
      this.userName = userName;
   }
   /**
    * @return the name
    */
   public String getName() {
      return name;
   }
   /**
    * @param name the name to set
    */
   public void setName(String name) {
      this.name = name;
   }
   /**
    * @return the equipement
    */
   public String getEquipement() {
      return equipement;
   }
   /**
    * @param equipement the equipement to set
    */
   public void setEquipement(String equipement) {
      this.equipement = equipement;
   }
   /**
    * @return the countryCode
    */
   public String getCountryCode() {
      return countryCode;
   }
   /**
    * @param countryCode the countryCode to set
    */
   public void setCountryCode(String countryCode) {
      this.countryCode = countryCode;
   }
   /**
    * @return the email
    */
   public String getEmail() {
      return email;
   }
   /**
    * @param email the email to set
    */
   public void setEmail(String email) {
      this.email = email;
   }
   /**
    * @return the phoneNumber
    */
   public String getPhoneNumber() {
      return phoneNumber;
   }
   /**
    * @param phoneNumber the phoneNumber to set
    */
   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }
   /**
    * @return the signupTimstamp
    */
   public Timestamp getSignupTimstamp() {
      return signupTimstamp;
   }
   /**
    * @param signupTimstamp the signupTimstamp to set
    */
   public void setSignupTimstamp(Timestamp signupTimstamp) {
      this.signupTimstamp = signupTimstamp;
   }
   /**
    * @return the lastOnline
    */
   public Timestamp getLastOnline() {
      return lastOnline;
   }
   /**
    * @param lastOnline the lastOnline to set
    */
   public void setLastOnline(Timestamp lastOnline) {
      this.lastOnline = lastOnline;
   }
   /**
    * @return the latitude
    */
   public double getLatitude() {
      return latitude;
   }
   /**
    * @param latitude the latitude to set
    */
   public void setLatitude(double latitude) {
      this.latitude = latitude;
   }
   /**
    * @return the longitude
    */
   public double getLongitude() {
      return longitude;
   }
   /**
    * @param longitude the longitude to set
    */
   public void setLongitude(double longitude) {
      this.longitude = longitude;
   }
   
}
