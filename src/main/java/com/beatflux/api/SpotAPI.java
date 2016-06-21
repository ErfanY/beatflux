package com.beatflux.api;

import java.util.ArrayList;
import java.util.List;

import com.beatflux.db.dal.SpotDAL;
import com.beatflux.db.to.SpotTO;
import com.beatflux.rest.objects.Spot;

public class SpotAPI {
   public List<Spot> getSpotList(){
      SpotDAL dal = new SpotDAL();
      List<SpotTO> sts = dal.listSpots();
      List<Spot> spots = (List<Spot>) new ArrayList<Spot>();
      for (SpotTO st: sts){
         Spot spot = new Spot();
         spot.setSpotID(st.getSpotID()); 
         spot.setUserName(st.getUserName());
         spot.setName(st.getName());
         spot.setEquipement(st.getEquipement());
         spot.setCountryCode(st.getCountryCode());
         spot.setEmail(st.getEmail());
         spot.setPhoneNumber(st.getPhoneNumber());
         spot.setSignupTimstamp(st.getSignupTimstamp());
         spot.setLastOnline(st.getLastOnline());
         spot.setLatitude(st.getLatitude());
         spot.setLongitude(st.getLongitude());
         spots.add(spot);
      }
      return spots;

   }

   public Spot searchSpot(int id){
      SpotDAL dal = new SpotDAL();
      Spot spot = new Spot();

      SpotTO s = dal.searchRecord(id); 
      if (s != null){
         spot.setSpotID(s.getSpotID());
         spot.setUserName(s.getUserName());
         spot.setName(s.getName());
         spot.setEquipement(s.getEquipement());
         spot.setCountryCode(s.getCountryCode());
         spot.setEmail(s.getEmail());
         spot.setPhoneNumber(s.getPhoneNumber());
         spot.setSignupTimstamp(s.getSignupTimstamp());
         spot.setLastOnline(s.getLastOnline());
         spot.setLatitude(s.getLatitude());
         spot.setLongitude(s.getLongitude());
         return spot;
      } else
         return null;
   }

   public void deleteSpot(int id){
      SpotDAL dal = new SpotDAL();
      dal.deleteSpot(id);
   }

   public void addSpot(Spot spot){
      SpotDAL dal = new SpotDAL();
      SpotTO s = new SpotTO();

      s.setSpotID(spot.getSpotID());
      s.setUserName(spot.getUserName());
      s.setName(spot.getName());
      s.setEquipement(spot.getEquipement());
      s.setCountryCode(spot.getCountryCode());
      s.setEmail(spot.getEmail());
      s.setPhoneNumber(spot.getPhoneNumber());
      s.setSignupTimstamp(spot.getSignupTimstamp());
      s.setLastOnline(spot.getLastOnline());
      s.setLatitude(spot.getLatitude());
      s.setLongitude(spot.getLongitude());
      s.setPassword("default");
      s.setPasswordSalt("default");
      dal.addSpot(s);
   }

   public boolean checkUser(int id){
      SpotDAL dal = new SpotDAL();
      if (dal.checkRecord(id))
         return true;
      else
         return false;
   }

   public void updateSpot(Spot spot){
      SpotDAL dal = new SpotDAL();
      SpotTO s = new SpotTO();

      s.setSpotID(spot.getSpotID());
      s.setUserName(spot.getUserName());
      s.setName(spot.getName());
      s.setEquipement(spot.getEquipement());
      s.setCountryCode(spot.getCountryCode());
      s.setEmail(spot.getEmail());
      s.setPhoneNumber(spot.getPhoneNumber());
      s.setSignupTimstamp(spot.getSignupTimstamp());
      s.setLastOnline(spot.getLastOnline());
      s.setLatitude(spot.getLatitude());
      s.setLongitude(spot.getLongitude());
      s.setPassword("default");
      s.setPasswordSalt("default");
      dal.updateSpot(s);      
   }

}
