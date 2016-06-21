package com.beatflux.db.to;


import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SpotTO {
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
   
   @XmlAttribute
   public int getSpotID() {
      return spotID;
   }


   public void setSpotID(int userID) {
      this.spotID = userID;
   }
   
   @XmlAttribute
   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   @XmlAttribute
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }


   @XmlAttribute
   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @XmlAttribute
   public String getPasswordSalt() {
      return passwordSalt;
   }

   public void setPasswordSalt(String passwordSalt) {
      this.passwordSalt = passwordSalt;
   }

   @XmlAttribute
   public String getCountryCode() {
      return countryCode;
   }

   public void setCountryCode(String countryCode) {
      this.countryCode = countryCode;
   }


   @XmlAttribute
   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @XmlAttribute
   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String mobileNumber) {
      this.phoneNumber = mobileNumber;
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


   public String getEquipement() {
      return equipement;
   }


   public void setEquipement(String equipement) {
      this.equipement = equipement;
   }
}
