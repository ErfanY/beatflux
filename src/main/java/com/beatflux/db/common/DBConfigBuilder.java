package com.beatflux.db.common;

import com.beatflux.db.common.DBConfig.ConnectionPoolType;
import com.beatflux.db.common.DBConfig.DBProvider;

public class DBConfigBuilder {
   private ConnectionPoolType connectionPoolType;
   private DBProvider databaseProvider;
   private String user;
   private String password;
   private String databaseURI;
   private int minimumPoolSize;
   private int maximumPoolSize;
   private boolean autoCommit;
   private String driver;
   private boolean cachePreparedStatements;
   private int preparedStatementCacheSize;
   private int preparedStatementCacheLimit;
   private boolean useServerPrepareStatement;
   private int idleTimeout;
   private int maxLifetime;
   private String characterEncoding;
   private boolean useUnicode;
   private int leakDetectionThreshold;
   
   /**
    * Get new instance of builder
    * @param useDefaults - Load default configuration
    * @return DBConfigBuilder
    */
   static DBConfigBuilder getBuilder(boolean useDefaults) {
      return new DBConfigBuilder(useDefaults);
   }
   
   private DBConfigBuilder(boolean useDefaults) {
      if (useDefaults) {
         // Set defaults
         connectionPoolType = ConnectionPoolType.HikariCP;
         databaseProvider = DBProvider.MySQL;
         minimumPoolSize = 10;
         maximumPoolSize = 10;
         autoCommit = false;
         driver = "com.mysql.jdbc.Driver";
         cachePreparedStatements = true;
         preparedStatementCacheSize = 250;
         preparedStatementCacheLimit = 2048;
         useServerPrepareStatement = true;
         idleTimeout = 28380000;
         maxLifetime = 28740000;
         characterEncoding = "UTF-8";
         useUnicode = true;
         leakDetectionThreshold = 10000;
      }
   }
   DBConfig build() {
      requireNotNullAndEmpty(user, "User cannot be null/empty");
      requireNotNullAndEmpty(password, "Password cannot be null/empty");
      requireNotNullAndEmpty(databaseURI, "DatabaseURI cannot be null/empty");
      return new DBConfig(connectionPoolType, databaseProvider, 
            user, password, databaseURI, minimumPoolSize, maximumPoolSize, 
            autoCommit, driver, cachePreparedStatements, preparedStatementCacheSize, 
            preparedStatementCacheLimit, useServerPrepareStatement, idleTimeout, 
            maxLifetime, characterEncoding, useUnicode, leakDetectionThreshold);
   }
   private void requireNotNullAndEmpty(String s, String message) {
      if (s == null || s.isEmpty()) throw new RuntimeException(message);
   }
   /**
    * @param connectionPoolType the connectionPoolType to set
    */
   DBConfigBuilder setConnectionPoolType(ConnectionPoolType connectionPoolType) {
      this.connectionPoolType = connectionPoolType;
      return this;
   }
   /**
    * @param databaseProvider the databaseProvider to set
    */
   DBConfigBuilder setDatabaseProvider(DBProvider databaseProvider) {
      this.databaseProvider = databaseProvider;
      return this;
   }
   /**
    * @param user the user to set
    */
   DBConfigBuilder setUser(String user) {
      this.user = user;
      return this;
   }
   /**
    * @param password the password to set
    */
   DBConfigBuilder setPassword(String password) {
      this.password = password;
      return this;
   }
   /**
    * @param databaseURI the databaseURI to set
    */
   DBConfigBuilder setDatabaseURI(String databaseURI) {
      this.databaseURI = databaseURI;
      return this;
   }
   /**
    * @param poolMinSize the poolMinSize to set
    */
   DBConfigBuilder setMinimumPoolSize(int minimumPoolSize) {
      this.minimumPoolSize = minimumPoolSize;
      return this;
   }
   /**
    * @param poolMaxSize the poolMaxSize to set
    */
   DBConfigBuilder setMaximumPoolSize(int maximumPoolSize) {
      this.maximumPoolSize = maximumPoolSize;
      return this;
   }
   /**
    * @param autoCommit the autoCommit to set
    */
   DBConfigBuilder setAutoCommit(boolean autoCommit) {
      this.autoCommit = autoCommit;
      return this;
   }
   /**
    * @param driver the driver to set
    */
   DBConfigBuilder setDriver(String driver) {
      this.driver = driver;
      return this;
   }
   /**
    * @param cachePreparedStatements the cachePreparedStatements to set
    */
   DBConfigBuilder setCachePreparedStatements(boolean cachePreparedStatements) {
      this.cachePreparedStatements = cachePreparedStatements;
      return this;
   }
   /**
    * @param preparedStatementCacheSize the preparedStatementCacheSize to set
    */
   DBConfigBuilder setPreparedStatementCacheSize(int preparedStatementCacheSize) {
      this.preparedStatementCacheSize = preparedStatementCacheSize;
      return this;
   }
   /**
    * @param preparedStatementCacheLimit the preparedStatementCacheLimit to set
    */
   DBConfigBuilder setPreparedStatementCacheLimit(int preparedStatementCacheLimit) {
      this.preparedStatementCacheLimit = preparedStatementCacheLimit;
      return this;
   }
   /**
    * @param useServerPrepareStatement the useServerPrepareStatement to set
    */
   DBConfigBuilder setUseServerPrepareStatement(boolean useServerPrepareStatement) {
      this.useServerPrepareStatement = useServerPrepareStatement;
      return this;
   }
   /**
    * @param idleTimeout the idleTimeout to set
    */
   DBConfigBuilder setIdleTimeout(int idleTimeout) {
      this.idleTimeout = idleTimeout;
      return this;
   }
   /**
    * @param maxLifetime the maxLifetime to set
    */
   DBConfigBuilder setMaxLifetime(int maxLifetime) {
      this.maxLifetime = maxLifetime;
      return this;
   }
   /**
    * @param characterEncoding the characterEncoding to set
    */
   DBConfigBuilder setCharacterEncoding(String characterEncoding) {
      this.characterEncoding = characterEncoding;
      return this;
   }
   /**
    * @param useUnicode the useUnicode to set
    */
   DBConfigBuilder setUseUnicode(boolean useUnicode) {
      this.useUnicode = useUnicode;
      return this;
   }
   /**
    * @param leakDetectionThreshold the leakDetectionThreshold to set
    */
   DBConfigBuilder setLeakDetectionThreshold(int leakDetectionThreshold) {
      this.leakDetectionThreshold = leakDetectionThreshold;
      return this;
   }
}
