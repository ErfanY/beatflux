package com.beatflux.db.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beatflux.db.common.ConnectionManager;
import com.beatflux.db.common.DBUtils;
import com.beatflux.db.to.SpotTO;
import com.beatflux.db.to.UserTO;
import com.beatflux.encrypt.BCryptHashImp;
import com.beatflux.encrypt.IHashGenerator;

public class SpotDAL {
   private IHashGenerator hashTool = new BCryptHashImp();
	/*
	 * @return list SpotTOs
	 */
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
				spot.setEquipment(rs.getString("equipment"));
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
	/*
	 * @param SpotTO object
	 */
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
			ps.setString(3, spot.getEquipment());
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
	/*
	 * @param spot_id int
	 */
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
	/*
	 * @param SpotTO object
	 */
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
			ps.setString(3, spot.getEquipment());
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
	/*
	 * @param spot_id int
	 * @return boolean
	 */
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
	/*
	 * @param spot_id int
	 * @return SpotTO object
	 */
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
				spot.setEquipment(rs.getString("equipment"));
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
			throw new RuntimeException(e);
		} finally {
			DBUtils.safeClose(rs);
			DBUtils.safeClose(ps);
			DBUtils.safeClose(conn);
		}
	}
	
	 /**
    * Check if spot's email exist in DB
    * @param email
    * @return true if email exist, false otherwise
    */
   public boolean emailExist(String email) {
         String query = "SELECT password FROM spots where email = ?";
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
   
   /**
    * Check if spot already exist in DB using email and password
    * @param email
    * @param password
    * @return true if spot exist otherwise return false
    */
   public boolean spotExist(String email, String password) {
      String query = "SELECT password FROM spots where email = ?";
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
    * @return SpotTO by email
    */
   public SpotTO getSpot(String email) {
      SpotTO spot = null;
      String query = "SELECT * FROM spots WHERE email = ?";
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
            spot = new SpotTO();
            spot.setSpotID(rs.getInt("spot_id"));
            spot.setUserName(rs.getString("username"));
            spot.setName(rs.getString("name"));
            spot.setEquipment(rs.getString("equipment"));
            spot.setPassword(rs.getString("password"));
            spot.setPasswordSalt(rs.getString("password_salt"));
            spot.setCountryCode(rs.getString("country_code"));
            spot.setEmail(rs.getString("email"));
            spot.setPhoneNumber(rs.getString("phone_number"));
            spot.setSignupTimstamp(rs.getTimestamp("signup_timestamp"));
            spot.setLastOnline(rs.getTimestamp("last_online"));
            spot.setLatitude(rs.getDouble("latitude"));
            spot.setLongitude(rs.getDouble("longitude"));
         }
      } catch(SQLException e) {
         throw new RuntimeException(e);
      } finally {
         DBUtils.safeClose(rs);
         DBUtils.safeClose(ps);
         DBUtils.safeClose(conn);
      }
      return spot;
   }
}
