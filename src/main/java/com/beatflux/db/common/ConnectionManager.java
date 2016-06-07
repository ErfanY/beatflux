package com.beatflux.db.common;

import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionManager {
   private static IConnectionPool pool;
   static {
      synchronized(ConnectionManager.class) {
         pool = new HikariCP(DBConfig.fdi_dev);
      }
   }
   
   public static Connection getConnection() throws SQLException {
      return pool.getConnection();
   }
}
