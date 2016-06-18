package com.beatflux.db.common;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * This class is responsible for handling HikariCP implementation of a connection pool.
 */
public class HikariCP implements IConnectionPool {
   private static final Logger logger = LoggerFactory.getLogger(HikariCP.class);
   private final HikariDataSource hds;
   /**
    * Constructor Limited to the package since it must be only used by
    * {@link ConnectionManager}
    */
   HikariCP(DBConfig dbc) {
      try {
         hds = new HikariDataSource(buildHikariConfig(dbc));
         logger.info("Connection pool for database is created");
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   /**
    * Builds HikariConfig from given database configuration
    * @param dbc - Database configuration
    * @return HikariConfig based on given configuration
    */
   private HikariConfig buildHikariConfig(DBConfig dbc) {
      HikariConfig config = new HikariConfig();
      /*
       * Somehow tomcat doesn't like hikaricp accessing through datasource.
       * Would have been a favorable method, but we'll do it through normal
       * JDBC url for now. config.setDataSourceClassName(
       * "com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
       */
      config.setJdbcUrl(dbc.databaseURI);
      config.setUsername(dbc.user);
      config.setPassword(dbc.password);
      config.setIdleTimeout(dbc.idleTimeout);
      config.setMaxLifetime(dbc.maxLifetime);
      config.setDriverClassName(dbc.driver);
      config.setMinimumIdle(dbc.minimumPoolSize);
      config.setMaximumPoolSize(dbc.maximumPoolSize);
      config.setAutoCommit(dbc.autoCommit);
      config.addDataSourceProperty("cachePrepStmts", dbc.cachePreparedStatements);
      config.addDataSourceProperty("prepStmtCacheSize", dbc.preparedStatementCacheSize);
      config.addDataSourceProperty("prepStmtCacheSqlLimit", dbc.preparedStatementCacheLimit);
      config.addDataSourceProperty("useServerPrepStmts", dbc.useServerPrepareStatement);
      config.addDataSourceProperty("characterEncoding", dbc.characterEncoding);
      config.addDataSourceProperty("useUnicode", dbc.useUnicode);
      config.setLeakDetectionThreshold(dbc.leakDetectionThreshold);
      config.setConnectionTestQuery(dbc.testQuery);
      logger.info("Loading configuration for database");
      return config;
   }
   
   @Override
   public Connection getConnection() throws SQLException {
      return hds.getConnection();
   }

}
