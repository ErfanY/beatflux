package com.beatflux.db.common;

import org.junit.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManagerTest {

   @Test
   public void testConnectionPool() throws SQLException {
      Connection c = ConnectionManager.getConnection();
      assertNotNull(c);
      DBUtils.safeClose(c);
   }
}
