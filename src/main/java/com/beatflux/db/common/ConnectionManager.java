package com.beatflux.db.common;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class is responsible for giving out connections and handling pools
 */
public final class ConnectionManager {
   private static IConnectionPool pool;
   static {
      synchronized(ConnectionManager.class) {
         pool = new HikariCP(DBConfig.fdi_dev);
      }
   }
   
   /**
    * This class should not be instantiated and must be 
    * used in static way
    */
   private ConnectionManager() {}

   /**
    * Gets a connection to the database
    * @return Connection
    * @throws SQLException if failed to get a connection
    */
   public static Connection getConnection() throws SQLException {
      return pool.getConnection();
   }
}
