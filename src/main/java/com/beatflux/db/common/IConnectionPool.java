package com.beatflux.db.common;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This is an interface for all possible implementation of
 * a connection pool. All pools must implement this method
 * for giving out connections.
 */
public interface IConnectionPool {
   /**
    * Gets a connection to the database from the pool
    * @return Connection
    * @throws SQLException if failed to get a connection
    */
   public Connection getConnection() throws SQLException;
}
