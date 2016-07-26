package com.beatflux.db.to;

import java.sql.Timestamp;

public class CookieTO {

   private long userId;
   private String cookieName;
   private String cookieValue;
   private Timestamp expiryTimestamp;
   
   public Timestamp getExpiryTimestamp() {
      return expiryTimestamp;
   }
   public void setExpiryTimestamp(Timestamp expiryTimestamp) {
      this.expiryTimestamp = expiryTimestamp;
   }
   public String getCookieName() {
      return cookieName;
   }
   public void setCookieName(String cookiename) {
      this.cookieName = cookiename;
   }
   public String getCookieValue() {
      return cookieValue;
   }
   public void setCookieValue(String cookievalue) {
      this.cookieValue = cookievalue;
   }
   public long getUserId() {
      return userId;
   }
   public void setUserId(long userId) {
      this.userId = userId;
   }  
}
