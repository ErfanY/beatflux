package com.beatflux.db.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beatflux.db.common.ConnectionManager;
import com.beatflux.db.common.DBUtils;
import com.beatflux.db.to.CookieTO;

public class CookieDAL {
   /*
    * @return list of CookieTOs
    */
   public List<CookieTO> listCookies() {
      List<CookieTO> cookies = new ArrayList<CookieTO>();
      String query = "SELECT * FROM cookies";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         rs = ps.executeQuery();
         conn.commit();
         while (rs.next()) {
            CookieTO cookie = new CookieTO();
            cookie.setUserId(rs.getLong("user_id"));
            cookie.setCookieName(rs.getString("cookie_name"));
            cookie.setCookieValue(rs.getString("cookie_value"));
            cookie.setExpiryTimestamp(rs.getTimestamp("expiry_timestamp"));
            cookies.add(cookie);
         }
      } catch(SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(rs);
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
      return cookies;
   }
   /*
    * @param CookieTO object
    */
   public void addCookie(CookieTO cookie) {
      String query = "insert into cookies (user_id, cookie_name, cookie_value, expiry_timestamp) " + 
            "values (?,?,?,?)";
      Connection conn = null;
      PreparedStatement ps = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setLong(1, cookie.getUserId());
         ps.setString(2, cookie.getCookieName());
         ps.setString(3, cookie.getCookieValue());
         ps.setTimestamp(4, cookie.getExpiryTimestamp());
         ps.executeUpdate();
         conn.commit();
         ps.close();
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
   }
   /*
    * @param cookieValue String
    */
   public void deleteCookie(String cookieValue) {
      String query = "delete from cookies where cookie_value=?";
      Connection conn = null;
      PreparedStatement ps = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, cookieValue);
         ps.executeUpdate();
         conn.commit();
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
   }

   /*
    * @param cookieValue String
    * @return boolean
    */
   public CookieTO getCookie(String cookieValue) {
      String query = "SELECT * FROM cookies where cookie_value = ?";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, cookieValue);
         rs = ps.executeQuery();
         conn.commit();
         if (rs.next()) {
               CookieTO cookie = new CookieTO();
               cookie.setUserId(rs.getLong("user_id"));
               cookie.setCookieName(rs.getString("cookie_name"));
               cookie.setCookieValue(rs.getString("cookie_value"));
               cookie.setExpiryTimestamp(rs.getTimestamp("expiry_timestamp"));
               return cookie;
         } else {
            return null;
         }
      } catch(SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(rs);
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
   }
   
}
