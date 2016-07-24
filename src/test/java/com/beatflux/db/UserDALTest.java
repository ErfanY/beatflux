package com.beatflux.db;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

import com.beatflux.db.dal.UserDAL;
import com.beatflux.db.to.UserTO;

public class UserDALTest {
	UserDAL dal = new UserDAL();
	UserTO user = new UserTO();
	@Test 
	public void testListUsers() {
		List<UserTO> userList = dal.listUsers();
		assertFalse(userList.isEmpty());
	}
	
	@Test
	public void testAddUser() throws ParseException {
		java.util.Calendar cal = Calendar.getInstance();
		java.util.Date utilDate = new java.util.Date(); // your util date
		cal.setTime(utilDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);    
		java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
		long time = sqlDate.getTime();
		user.setUserName("test");
		user.setFirstName("Erfan");
		user.setLastName("Y.");
		user.setPassword("12345");
		user.setPasswordSalt("233");
		user.setCountryCode("se");
		user.setBirthDate(sqlDate);
		user.setEmail("test@test.com");
		user.setMobileNumber("1234567");
		user.setSignupTimstamp(new Timestamp(time));
		user.setLastOnline(new Timestamp(time));
		user.setLatitude(1.2);
		user.setLongitude(2.3);
		dal.addUser(user);
		List<UserTO> userList = dal.listUsers();
		assertTrue(userList.size() == 1);
	}
}
