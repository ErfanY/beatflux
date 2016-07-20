package com.beatflux.db.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.beatflux.db.common.ConnectionManager;
import com.beatflux.db.common.DBUtils;
import com.beatflux.db.to.UserTO;

public class UserDAL {
   /*
    * @return list of UserTOs
    */
   public List<UserTO> listUsers() {
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
   /*
    * @param UserTO object
    */
   public void addUser(UserTO user) {
      String query = "insert into users (username, firstname, lastname, password, password_salt, country_code, birthdate, " + 
            "email, mobile_number, signup_timestamp, last_online, latitude, longitude)" + 
            "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
         ps.setTimestamp(10, user.getSignupTimstamp());
         ps.setTimestamp(11, user.getLastOnline());
         ps.setDouble(12, user.getLatitude());
         ps.setDouble(13, user.getLongitude());
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
    * @param user_id int
    */
   public void deleteUser(int userId) {
      String query = "delete from users where user_id=?";
      Connection conn = null;
      PreparedStatement ps = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setInt(1, userId);
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
    * @param UserTO object
    */
   public void updateUser(UserTO user) {
      String query = "update users set username=?, firstname=?, lastname=?, password=?," +
            "birthdate=?, email=?, mobile_number=?, where user_id=?";
      Connection conn = null;
      PreparedStatement ps = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, user.getUserName());
         ps.setString(2, user.getFirstName());
         ps.setString(3, user.getLastName());
         ps.setString(4, user.getPassword());
         ps.setDate(5, user.getBirthDate());
         ps.setString(6, user.getEmail());
         ps.setString(7, user.getMobileNumber());
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
   /*
    * @param user_id int
    * @return boolean
    */
   public boolean checkRecord(int id) {
      String query = "SELECT * FROM users where user_id = ?";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setInt(1, id);
         rs = ps.executeQuery();
         conn.commit();
         if (rs.next()) {
            return true;
         } else {
            return false;
         }
      } catch(SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(rs);
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
   }
   /*
    * @param user_id int
    * @return UserTO object
    */
   public UserTO searchRecord(int id) {
      String query = "SELECT * FROM users where user_id = ?";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setInt(1, id);
         rs = ps.executeQuery();
         conn.commit();
         if (rs.next()) {
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
            return user;
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

   /*
    * @param email String
    * @return true if user exist otherwise return false
    */
   public boolean checkRecord(String email) {
      String query = "SELECT * FROM users where email = ?";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, email);
         conn.commit();
         rs = ps.executeQuery();
         if (rs.next()) {
            String pwd = rs.getString("password");
            String pwdUser = "default";
            if (BCrypt.checkpw(pwdUser, pwd)){
               System.out.println("It matches");
               return true;
            }
            else{
               System.out.println("It does not match");
               return false;
            }
         } else {
            return false;
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
