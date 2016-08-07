package com.beatflux.api;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.beatflux.db.dal.SpotDAL;
import com.beatflux.db.to.SpotTO;
import com.beatflux.encrypt.BCryptHashImp;
import com.beatflux.encrypt.IHashGenerator;
import com.beatflux.rest.objects.Spot;

public class SpotAPI {
   private IHashGenerator hashTool = new BCryptHashImp();
	/*
	 * @return list of Spots
	 */
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
	/*
	 * @param spot_id int
	 * @return Spot object
	 */
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
	/*
	 * @param spot_id int
	 */
	public void deleteSpot(int id){
		SpotDAL dal = new SpotDAL();
		dal.deleteSpot(id);
	}
	public void addSpot(Spot spot, String password){
	   String pwdSalt = hashTool.generateSalt(12);
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
		s.setPassword(hashTool.hash(password, pwdSalt));
      s.setPasswordSalt(pwdSalt);
		dal.addSpot(s);
	}
	/*
	 * @param spot_id int
	 */
	public boolean checkUser(int id){
		SpotDAL dal = new SpotDAL();
		if (dal.checkRecord(id))
			return true;
		else
			return false;
	}
	/*
	 * @param Spot object
	 */
	public void updateSpot(Spot spot, String password){
	   String pwdSalt = hashTool.generateSalt(12);
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
		s.setPassword(hashTool.hash(password, pwdSalt));
      s.setPasswordSalt(pwdSalt);
		dal.updateSpot(s);      
	}
	 
	 public boolean emailExist(String email) {
	      SpotDAL dal = new SpotDAL();
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
