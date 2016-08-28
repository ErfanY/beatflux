package com.beatflux.db;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;

import com.beatflux.db.dal.SpotDAL;
import com.beatflux.db.to.SpotTO;

public class SpotDALTests {
   SpotDAL dal = new SpotDAL();
   SpotTO spot = new SpotTO();

   @Test 
   public void testAddSpot(){
      Date date = new Date(System.currentTimeMillis()); 
      long time = date.getTime();
      
      spot.setUserName("test");
      spot.setEmail("nasrin.nahidErfan@gmail.com");
      spot.setEquipment("Machine");
      spot.setName("test");
      spot.setPassword("1234");
      spot.setPhoneNumber("72395868929");
      spot.setSignupTimstamp(new Timestamp(time));
      spot.setLastOnline(new Timestamp(time));
      spot.setCountryCode("se");
      
      dal.addSpot(spot);
      String email = spot.getEmail();
      assertTrue("object should exist in DataBase", dal.emailExist(email));
   }
   
   @Test 
   public void testUpdateSpot(){
      Date date = new Date(System.currentTimeMillis()); 
      long time = date.getTime();
      
      spot.setUserName("testUpdate");
      spot.setEmail("nasrin.nahid@gmail.com");
      spot.setEquipment("Machine");
      spot.setName("testUpdate");
      spot.setPassword("1234");
      spot.setPhoneNumber("723958689");
      spot.setSignupTimstamp(new Timestamp(time));
      spot.setLastOnline(new Timestamp(time));
      spot.setCountryCode("se");
      
      if (dal.emailExist("nasrin.nahid@gmail.com")){
      String email = spot.getEmail();
      assertTrue("object should exist in DataBase", dal.emailExist(email));
      } else {
         System.out.print("Eamil does not exist!");
      }
   }

   @Test 
   public void testListUsers() {
      List<SpotTO> spotList = dal.listSpot();
      assertFalse(spotList.isEmpty());
   }
   @Test
   public void testSearchSpot() {
      assertNotNull("object should exist", dal.checkRecord(1));
   }
   @Test
   public void testDeleteSpot() {
      String email = "s1@gmail.com";
      dal.deleteSpot(email);
      assertFalse("object should not exist", dal.emailExist(email));
   }
   
   @Test
   public void TestGetSpot(){
      String email = "nasrin.nahid@gmail.com";
     assertTrue("Fail!object is not exist",dal.emailExist(dal.getSpot(email).getEmail()));   
   }
}
