package com.beatflux.api;

import java.util.ArrayList;
import java.util.List;

import com.beatflux.db.dal.UserDAL;
import com.beatflux.db.to.UserTO;
import com.beatflux.rest.objects.User;

public class UserAPI {
   public List<User> getUserList(){
      UserDAL dal = new UserDAL();
      List<UserTO> s = dal.listUsers();
      List<User> user = (List<User>) new ArrayList<User>();
      for (int i=0; i<s.size();i++){
         user.get(i).setUserID(s.get(i).getUserID()); 
         user.get(i).setUserName(s.get(i).getUserName());
         user.get(i).setFirstName(s.get(i).getFirstName());
         user.get(i).setLastName(s.get(i).getLastName());
         user.get(i).setCountryCode(s.get(i).getCountryCode());
         user.get(i).setBirthDate(s.get(i).getBirthDate());
         user.get(i).setEmail(s.get(i).getEmail());
         user.get(i).setMobileNumber(s.get(i).getMobileNumber());
         user.get(i).setSignupTimstamp(s.get(i).getSignupTimstamp());
         user.get(i).setLastOnline(s.get(i).getLastOnline());
         user.get(i).setLatitude(s.get(i).getLatitude());
         user.get(i).setLongitude(s.get(i).getLongitude());
      }
      return user;

   }

   public User searchUser(int id){
      UserDAL dal = new UserDAL();
      User user = new User();

      UserTO s = dal.searchRecord(id); 
      if (s != null){
         user.setUserID(s.getUserID());
         user.setUserName(s.getUserName());
         user.setFirstName(s.getFirstName());
         user.setLastName(s.getLastName());
         user.setCountryCode(s.getCountryCode());
         user.setBirthDate(s.getBirthDate());
         user.setEmail(s.getEmail());
         user.setMobileNumber(s.getMobileNumber());
         user.setSignupTimstamp(s.getSignupTimstamp());
         user.setLastOnline(s.getLastOnline());
         user.setLatitude(s.getLatitude());
         user.setLongitude(s.getLongitude());
         return user;
      } else
         return null;
   }

   public void deleteUser(int id){
      UserDAL dal = new UserDAL();
      dal.deleteUser(id);
   }

   public void addUser(User user){
      UserDAL dal = new UserDAL();
      UserTO s = new UserTO();
      
      s.setUserID(user.getUserID());
      s.setUserName(user.getUserName());
      s.setFirstName(user.getFirstName());
      s.setLastName(user.getLastName());
      s.setCountryCode(user.getCountryCode());
      s.setBirthDate(user.getBirthDate());
      s.setEmail(user.getEmail());
      s.setMobileNumber(user.getMobileNumber());
      s.setSignupTimstamp(user.getSignupTimstamp());
      s.setLastOnline(user.getLastOnline());
      s.setLatitude(user.getLatitude());
      s.setLongitude(user.getLongitude());
      s.setPassword("default");
      s.setPasswordSalt("default");
      dal.addUser(s);
   }
   
   public boolean checkUser(int id){
      UserDAL dal = new UserDAL();
      if (dal.checkRecord(id))
         return true;
      else
         return false;
   }
   
   public void updateUser(User user){
      UserDAL dal = new UserDAL();
      UserTO s = new UserTO();
      
      s.setUserID(user.getUserID());
      s.setUserName(user.getUserName());
      s.setFirstName(user.getFirstName());
      s.setLastName(user.getLastName());
      s.setCountryCode(user.getCountryCode());
      s.setBirthDate(user.getBirthDate());
      s.setEmail(user.getEmail());
      s.setMobileNumber(user.getMobileNumber());
      s.setSignupTimstamp(user.getSignupTimstamp());
      s.setLastOnline(user.getLastOnline());
      s.setLatitude(user.getLatitude());
      s.setLongitude(user.getLongitude());
      s.setPassword("default");
      s.setPasswordSalt("default");
      dal.updateUser(s);      
   }
   
}
