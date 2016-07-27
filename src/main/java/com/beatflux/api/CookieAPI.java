package com.beatflux.api;

import java.security.SecureRandom;

import com.beatflux.common.AppConfig;
import com.beatflux.db.dal.CookieDAL;
import com.beatflux.db.to.CookieTO;

import java.sql.Timestamp;

public class CookieAPI {
   
   public void addCookie(long userId, String cookie_value, Timestamp expiryTimestamp) {
      CookieDAL cdal = new CookieDAL();
      CookieTO cookie = new CookieTO();
      cookie.setUserId(userId);
      cookie.setCookieName(AppConfig.APP_COOKIE_SECRET);
      cookie.setCookieValue(cookie_value);
      cookie.setExpiryTimestamp(expiryTimestamp);
      cdal.addCookie(cookie);
   }
   
   public void deleteCookie(String cookie_value) {
      CookieDAL dal = new CookieDAL();
      dal.deleteCookie(cookie_value);
   }

   public long validateCookie(String cookie_value) {
      CookieDAL cdal = new CookieDAL();
      CookieTO cookie = null;
      cookie = cdal.getCookie(cookie_value);
      if (cookie != null){
         return cookie.getUserId();
      }
      return 0;
   }
   
   static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   static SecureRandom rnd = new SecureRandom();

   public String randomString( int len ){
      StringBuilder sb = new StringBuilder( len );
      for( int i = 0; i < len; i++ ) 
         sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
      return sb.toString();
   }
}
