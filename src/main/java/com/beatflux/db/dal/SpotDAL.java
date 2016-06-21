package com.beatflux.db.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beatflux.db.common.ConnectionManager;
import com.beatflux.db.common.DBUtils;
import com.beatflux.db.to.SpotTO;

public class SpotDAL {
   private static final Logger logger = LoggerFactory.getLogger(SpotDAL.class);
   public List<SpotTO> listSpots() {
      List<SpotTO> spots = new ArrayList<SpotTO>();
      String query = "SELECT * FROM spots";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         rs = ps.executeQuery();
         while (rs.next()) {
            SpotTO spot = new SpotTO();
            spot.setSpotID(rs.getInt("spot_id"));
            spot.setUserName(rs.getString("username"));
            spot.setName(rs.getString("name"));
            spot.setEquipement(rs.getString("equipement"));
            spot.setPassword(rs.getString("password"));
            spot.setPasswordSalt(rs.getString("password_salt"));
            spot.setCountryCode(rs.getString("country_code"));
            spot.setEmail(rs.getString("email"));
            spot.setPhoneNumber(rs.getString("phone_number"));
            spot.setSignupTimstamp(rs.getTimestamp("signup_timestamp"));
            spot.setLastOnline(rs.getTimestamp("last_online"));
            spot.setLatitude(rs.getDouble("latitude"));
            spot.setLongitude(rs.getDouble("longitude"));
            spots.add(spot);
         }
      } catch(SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(rs);
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
      return spots;
   }

   public void addSpot(SpotTO spot) {
      String query = "insert into spots (username, name, equipement, password, password_salt, country_code " + 
            "email, phone_number, signup_timestamp, last_online, latitude, longitude)" + 
            "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      Connection conn = null;
      PreparedStatement ps = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, spot.getUserName());
         ps.setString(2, spot.getName());
         ps.setString(3, spot.getEquipement());
         ps.setString(4, spot.getPassword());
         ps.setString(5, spot.getPasswordSalt());
         ps.setString(6, spot.getCountryCode());
         ps.setString(7, spot.getEmail());
         ps.setString(8, spot.getPhoneNumber());
         ps.setTimestamp(9, spot.getSignupTimstamp());
         ps.setTimestamp(10, spot.getLastOnline());
         ps.setDouble(11, spot.getLatitude());
         ps.setDouble(12, spot.getLongitude());
         ps.executeUpdate();
         ps.close();
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
   }

   public void deleteSpot(int spotId) {
      String query = "delete from spots where spot_id=?";
      Connection conn = null;
      PreparedStatement ps = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setInt(1, spotId);
         ps.executeUpdate();
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
   }

   public void updateSpot(SpotTO spot) {
      String query = "update spots set username=?, name=?, equipement=?, password=?, password_salt=?" +
            "email=?, phone_number=?, where spot_id=?";
      Connection conn = null;
      PreparedStatement ps = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setString(1, spot.getUserName());
         ps.setString(2, spot.getName());
         ps.setString(3, spot.getEquipement());
         ps.setString(4, spot.getPassword());
         ps.setString(5, spot.getPasswordSalt());
         ps.setString(6, spot.getEmail());
         ps.setString(7, spot.getPhoneNumber());
         ps.executeUpdate();
         ps.close();
      } catch(SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
   }

   public boolean checkRecord(int id) {
      String query = "SELECT * FROM spots where spot_id = ?";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setInt(1, id);
         rs = ps.executeQuery();
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

   public SpotTO searchRecord(int id) {
      String query = "SELECT * FROM spots where spot_id = ?";
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      try {
         conn = ConnectionManager.getConnection();
         ps = conn.prepareStatement(query);
         ps.setInt(1, id);
         rs = ps.executeQuery();
         if (rs.next()) {
            SpotTO spot = new SpotTO();
            spot.setSpotID(rs.getInt("spot_id"));
            spot.setUserName(rs.getString("username"));
            spot.setName(rs.getString("name"));
            spot.setEquipement(rs.getString("equipement"));
            spot.setPassword(rs.getString("password"));
            spot.setPasswordSalt(rs.getString("password_salt"));
            spot.setCountryCode(rs.getString("country_code"));
            spot.setEmail(rs.getString("email"));
            spot.setPhoneNumber(rs.getString("phone_number"));
            spot.setSignupTimstamp(rs.getTimestamp("signup_timestamp"));
            spot.setLastOnline(rs.getTimestamp("last_online"));
            spot.setLatitude(rs.getDouble("latitude"));
            spot.setLongitude(rs.getDouble("longitude"));
            return spot;
         } else {
            return null;
         }
      } catch(SQLException e) {
         logger.warn("Failed to search record.", e);
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(rs);
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
   }
}
