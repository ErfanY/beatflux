package com.beatflux.db;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.beatflux.db.dal.SpotDAL;
import com.beatflux.db.to.SpotTO;

public class SpotDALTests {
	SpotDAL dal = new SpotDAL();
	SpotTO spot = new SpotTO();
	@Test 
	public void testListUsers() {
		List<SpotTO> spotList = dal.listSpots();
		assertFalse(spotList.isEmpty());
	}
	@Test
	public void testSearchSpot() {
		assertNotNull("object should exist", dal.searchRecord(1));
	}
	@Test
	public void testDeleteSpot() {
		dal.deleteSpot(1);
		assertNotNull("object should not exist", dal.searchRecord(1));
	}
}
