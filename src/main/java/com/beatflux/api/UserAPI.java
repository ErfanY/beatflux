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
   public void addUser(User user, String password) {
      String pwdSalt = hashTool.generateSalt(12);
      UserDAL dal = new UserDAL();
      UserTO userTo = new UserTO();
      userTo.setUserName(user.getUserName());
      userTo.setFirstName(user.getFirstName());
      userTo.setLastName(user.getLastName());
      userTo.setCountryCode(user.getCountryCode());
      userTo.setBirthDate(user.getBirthDate());
      userTo.setEmail(user.getEmail());
      userTo.setMobileNumber(user.getMobileNumber());
      userTo.setPassword(hashTool.hash(password, pwdSalt));
      userTo.setPasswordSalt(pwdSalt);
      dal.addUser(userTo);
   }
   public List<User> listUser() {
      UserDAL dal = new UserDAL();
      List<UserTO> uts = dal.listUser();
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
   public void updateUser(User user, String password) {
      String pwdSalt = hashTool.generateSalt(12);
      UserDAL dal = new UserDAL();
      UserTO userTo = new UserTO();
      userTo.setUserName(user.getUserName());
      userTo.setFirstName(user.getFirstName());
      userTo.setLastName(user.getLastName());
      userTo.setCountryCode(user.getCountryCode());
      userTo.setBirthDate(user.getBirthDate());
      userTo.setEmail(user.getEmail());
      userTo.setMobileNumber(user.getMobileNumber());
      userTo.setPassword(hashTool.hash(password, pwdSalt));
      userTo.setPasswordSalt(pwdSalt);
      dal.updateUser(userTo);
   }
   public void deleteUser(String email) {
      UserDAL dal = new UserDAL();
      dal.deleteUser(email);
   }
   public User searchUser(int id){
      UserDAL dal = new UserDAL();
      User user = new User();
      UserTO userTo = dal.searchUser(id); 
      if (userTo != null){
         user.setUserID(userTo.getUserID());
         user.setUserName(userTo.getUserName());
         user.setFirstName(userTo.getFirstName());
         user.setLastName(userTo.getLastName());
         user.setCountryCode(userTo.getCountryCode());
         user.setBirthDate(userTo.getBirthDate());
         user.setEmail(userTo.getEmail());
         user.setMobileNumber(userTo.getMobileNumber());
         user.setSignupTimstamp(userTo.getSignupTimstamp());
         user.setLastOnline(userTo.getLastOnline());
         user.setLatitude(userTo.getLatitude());
         user.setLongitude(userTo.getLongitude());
         return user;
      } else
         return null;
   }
   public User getUser(long userId) {
      UserDAL dal = new UserDAL();
      UserTO userTO = null;
      User user = null;
      userTO = dal.getUser(userId);
      if (userTO != null) {
         user = new User();
         user.setUserID(userTO.getUserID());
         user.setUserName(userTO.getUserName());
         user.setFirstName(userTO.getFirstName());
         user.setLastName(userTO.getLastName());
         user.setCountryCode(userTO.getCountryCode());
         user.setBirthDate(userTO.getBirthDate());
         user.setEmail(userTO.getEmail());
         user.setMobileNumber(userTO.getMobileNumber());
         user.setSignupTimstamp(userTO.getSignupTimstamp());
         user.setLastOnline(userTO.getLastOnline());
         user.setLatitude(userTO.getLatitude());
         user.setLongitude(userTO.getLongitude());
      }
      return user;
   }
   public User getUser(String email) {
      UserDAL dal = new UserDAL();
      UserTO userTO = null;
      User user = null;
      userTO = dal.getUser(email);
      if (userTO != null) {
         user = new User();
         user.setUserID(userTO.getUserID());
         user.setUserName(userTO.getUserName());
         user.setFirstName(userTO.getFirstName());
         user.setLastName(userTO.getLastName());
         user.setCountryCode(userTO.getCountryCode());
         user.setBirthDate(userTO.getBirthDate());
         user.setEmail(userTO.getEmail());
         user.setMobileNumber(userTO.getMobileNumber());
         user.setSignupTimstamp(userTO.getSignupTimstamp());
         user.setLastOnline(userTO.getLastOnline());
         user.setLatitude(userTO.getLatitude());
         user.setLongitude(userTO.getLongitude());
      }
      return user;
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