package com.beatflux.db.to;

public class CookieTO {

   private int userId;
   private String cookieName;
   private String cookieValue;
   
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
   public int getUserId() {
      return userId;
   }
   public void setUserId(int userId) {
      this.userId = userId;
   }  
}
