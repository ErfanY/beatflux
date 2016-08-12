package com.beatflux.db.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beatflux.db.common.ConnectionManager;
import com.beatflux.db.common.DBUtils;
import com.beatflux.db.to.UserTO;
import com.beatflux.encrypt.BCryptHashImp;
import com.beatflux.encrypt.IHashGenerator;

public class UserDAL {
   private IHashGenerator hashTool = new BCryptHashImp();
   /**
    * Add new user into DB
    * @param UserTO 
    */
   public void addUser(UserTO user) {
      String query = "insert into users (username, firstname, lastname, password, password_salt, country_code, birthdate, " + 
            "email, mobile_number)" + 
            "values (?,?,?,?,?,?,?,?,?)";
      Connection conn = null;
      PreparedStatement ps = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, user.getUserName());
         ps.setString(2, user.getFirstName());
         ps.setString(3, user.getLastName());
         ps.setString(4, user.getPassword());
         ps.setString(5, user.getPasswordSalt());
         ps.setString(6, user.getCountryCode());
         ps.setDate(7, user.getBirthDate());
         ps.setString(8, user.getEmail());
         ps.setString(9, user.getMobileNumber());
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
   /**
    * @return list of UserTOs
    */
   public List<UserTO> listUser() {
      List<UserTO> users = new ArrayList<UserTO>();
      String query = "SELECT * FROM users";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         rs = ps.executeQuery();
         conn.commit();
         while (rs.next()) {
            UserTO user = new UserTO();
            user.setUserID(rs.getInt("user_id"));
            user.setUserName(rs.getString("username"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setPassword(rs.getString("password"));
            user.setPasswordSalt(rs.getString("password_salt"));
            user.setCountryCode(rs.getString("country_code"));
            user.setBirthDate(rs.getDate("birthdate"));
            user.setEmail(rs.getString("email"));
            user.setMobileNumber(rs.getString("mobile_number"));
            user.setSignupTimstamp(rs.getTimestamp("signup_timestamp"));
            user.setLastOnline(rs.getTimestamp("last_online"));
            user.setLatitude(rs.getDouble("latitude"));
            user.setLongitude(rs.getDouble("longitude"));
            users.add(user);
         }
      } catch(SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(rs);
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
      return users;
   }
   /**
    * @param UserTO 
    */
   public void updateUser(UserTO user) {
      String query = "update users set username=?, firstname=?, lastname=?, password=?, " +
            "country_code = ?, birthdate=?, mobile_number=? where email=?";
      Connection conn = null;
      PreparedStatement ps = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, user.getUserName());
         ps.setString(2, user.getFirstName());
         ps.setString(3, user.getLastName());
         ps.setString(4, user.getPassword());
         ps.setString(5, user.getCountryCode());
         ps.setDate(6, user.getBirthDate());
         ps.setString(7, user.getMobileNumber());
         ps.setString(8, user.getEmail());
         ps.executeUpdate();
         conn.commit();
         ps.close();
      } catch(SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
   }
   /**
    * Delete a record from DB using email and password
    * @param email
    * @param password
    */
   public void deleteUser(String email) {
      String query = "delete from users where email=?";
      Connection conn = null;
      PreparedStatement ps = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, email);
         ps.executeUpdate();
         conn.commit();
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
   }
   /**
    * @param user_id int
    * @return UserTO object
    */
   public UserTO searchUser(int id) {
      String query = "SELECT * FROM users where user_id = ?";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setInt(1, id);
         rs = ps.executeQuery();
         if (rs.next()) {
            UserTO userTo = new UserTO();
            userTo.setUserID(rs.getInt("user_id"));
            userTo.setUserName(rs.getString("username"));
            userTo.setFirstName(rs.getString("firstname"));
            userTo.setLastName(rs.getString("lastname"));
            userTo.setPassword(rs.getString("password"));
            userTo.setPasswordSalt(rs.getString("password_salt"));
            userTo.setCountryCode(rs.getString("country_code"));
            userTo.setEmail(rs.getString("email"));
            userTo.setMobileNumber(rs.getString("mobile_number"));
            userTo.setSignupTimstamp(rs.getTimestamp("signup_timestamp"));
            userTo.setLastOnline(rs.getTimestamp("last_online"));
            userTo.setLatitude(rs.getDouble("latitude"));
            userTo.setLongitude(rs.getDouble("longitude"));
            return userTo;
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
   /**
    * @return list of UserTOs
    */
   public UserTO getUser(long userId) {
      UserTO user = null;
      String query = "SELECT * FROM users WHERE user_id = ?";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setLong(1, userId);
         rs = ps.executeQuery();
         conn.commit();
         while (rs.next()) {
            user = new UserTO();
            user.setUserID(rs.getInt("user_id"));
            user.setUserName(rs.getString("username"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setPassword(rs.getString("password"));
            user.setPasswordSalt(rs.getString("password_salt"));
            user.setCountryCode(rs.getString("country_code"));
            user.setBirthDate(rs.getDate("birthdate"));
            user.setEmail(rs.getString("email"));
            user.setMobileNumber(rs.getString("mobile_number"));
            user.setSignupTimstamp(rs.getTimestamp("signup_timestamp"));
            user.setLastOnline(rs.getTimestamp("last_online"));
            user.setLatitude(rs.getDouble("latitude"));
            user.setLongitude(rs.getDouble("longitude"));
         }
      } catch(SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(rs);
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
      return user;
   }
   /**
    * @return UserTO by email
    */
   public UserTO getUser(String email) {
      UserTO user = null;
      String query = "SELECT * FROM users WHERE email = ?";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, email);
         rs = ps.executeQuery();
         conn.commit();
         if (rs.next()) {
            user = new UserTO();
            user.setUserID(rs.getInt("user_id"));
            user.setUserName(rs.getString("username"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setPassword(rs.getString("password"));
            user.setPasswordSalt(rs.getString("password_salt"));
            user.setCountryCode(rs.getString("country_code"));
            user.setBirthDate(rs.getDate("birthdate"));
            user.setEmail(rs.getString("email"));
            user.setMobileNumber(rs.getString("mobile_number"));
            user.setSignupTimstamp(rs.getTimestamp("signup_timestamp"));
            user.setLastOnline(rs.getTimestamp("last_online"));
            user.setLatitude(rs.getDouble("latitude"));
            user.setLongitude(rs.getDouble("longitude"));
         }
      } catch(SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(rs);
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
      return user;
   }
   /**
    * Check if user already exist in DB using email and password
    * @param email
    * @param password
    * @return true if user exist otherwise return false
    */
   public boolean userExist(String email, String password) {
      String query = "SELECT password FROM users where email = ?";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, email);
         conn.commit();
         rs = ps.executeQuery();
         return rs.next() && hashTool.checkHash(password, rs.getString("password"));
      } catch(SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(rs);
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
   }
   /**
    * Check if user's email exist in DB
    * @param email
    * @return true if email exist, false otherwise
    */
   public boolean emailExist(String email) {
      String query = "SELECT password FROM users where email = ?";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, email);
         conn.commit();
         rs = ps.executeQuery();
         return rs.next();
      } catch(SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(rs);
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
   }
}
