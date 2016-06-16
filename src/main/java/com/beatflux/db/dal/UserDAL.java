package com.beatflux.db.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beatflux.db.common.ConnectionManager;
import com.beatflux.db.common.DBUtils;
import com.beatflux.objects.User;

public class UserDAL {
	public List<User> listUsers() {
		List<User> users = new ArrayList<User>();
		String query = "SELECT * FROM users";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
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

	public void addUser(User user) {
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
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtils.safeClose(ps);
			DBUtils.safeClose(conn);
		}
	}

	public void deleteUser(int userId) {
		String query = "delete from users where user_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtils.safeClose(ps);
			DBUtils.safeClose(conn);
		}
	}

	public void updateUser(User user) {
		String query = "update users set username=?, firstname=?, lastname=?, password=?," +
				"birthdate=?, email=?, mobile_number=?, where user_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionManager.getConnection();
			ps = conn.prepareStatement(query);
			ps = conn.prepareStatement(query);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getPassword());
			ps.setDate(5, user.getBirthDate());
			ps.setString(6, user.getEmail());
			ps.setString(7, user.getMobileNumber());
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
}
