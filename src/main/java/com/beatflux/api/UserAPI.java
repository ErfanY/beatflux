package com.beatflux.api;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.beatflux.db.dal.UserDAL;
import com.beatflux.db.to.UserTO;
import com.beatflux.encrypt.BCryptHashImp;
import com.beatflux.encrypt.IHashGenerator;
import com.beatflux.rest.objects.User;

public class UserAPI {
   private IHashGenerator hashTool = new BCryptHashImp();
   /**
    * @return list of Users
    */
   public List<User> getUserList() {
      UserDAL dal = new UserDAL();
      List<UserTO> uts = dal.listUsers();
      List<User> users = (List<User>) new ArrayList<User>();
      for (UserTO ut : uts) {
         User user = new User();
         user.setUserID(ut.getUserID());
         user.setUserName(ut.getUserName());
         user.setFirstName(ut.getFirstName());
         user.setLastName(ut.getLastName());
         user.setCountryCode(ut.getCountryCode());
         user.setBirthDate(ut.getBirthDate());
         user.setEmail(ut.getEmail());
         user.setMobileNumber(ut.getMobileNumber());
         user.setSignupTimstamp(ut.getSignupTimstamp());
         user.setLastOnline(ut.getLastOnline());
         user.setLatitude(ut.getLatitude());
         user.setLongitude(ut.getLongitude());
         users.add(user);
      }
      return users;
   }
   public void deleteUser(String email) {
      UserDAL dal = new UserDAL();
      dal.deleteUser(email);
   }
   public void addUser(User user, String password) {
      String pwdSalt = hashTool.generateSalt(12);
      UserDAL dal = new UserDAL();
      UserTO ut = new UserTO();
      ut.setUserName(user.getUserName());
      ut.setFirstName(user.getFirstName());
      ut.setLastName(user.getLastName());
      ut.setCountryCode(user.getCountryCode());
      ut.setBirthDate(user.getBirthDate());
      ut.setEmail(user.getEmail());
      ut.setMobileNumber(user.getMobileNumber());
      ut.setPassword(hashTool.hash(password, pwdSalt));
      ut.setPasswordSalt(pwdSalt);
      dal.addUser(ut);
   }
   public boolean userExist(String email, String password) {
      UserDAL dal = new UserDAL();
      return dal.userExist(email, password);
   }
   public boolean emailExist(String email) {
	   UserDAL dal = new UserDAL();
	   return dal.emailExist(email);
   }
   public boolean isValidEmail(String email) {
	   try {
		InternetAddress emailAddr = new InternetAddress(email);
		emailAddr.validate();
		return true;
	} catch (AddressException e) {
		e.printStackTrace();
		return false;
	}
   }
}