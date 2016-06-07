package com.beatflux.db.common;

public class DBConfig {
   /** Connection pool type. */
   enum ConnectionPoolType {
      HikariCP
   };

   /** Database provider */
   public enum DBProvider {
      MySQL,
   };
   final ConnectionPoolType connectionPoolType;
   final DBProvider databaseProvider;
   final String user;
   final String password;
   final String databaseURI;
   final int minimumPoolSize;
   final int maximumPoolSize;
   final boolean autoCommit;
   final String driver;
   final boolean cachePreparedStatements;
   final int preparedStatementCacheSize;
   final int preparedStatementCacheLimit;
   final boolean useServerPrepareStatement;
   final int idleTimeout;
   final int maxLifetime;
   final String characterEncoding;
   final boolean useUnicode;
   final int leakDetectionThreshold;
   /**
    * @param connectionPoolType
    * @param databaseProvider
    * @param user
    * @param password
    * @param databaseURI
    * @param poolMinSize
    * @param poolMaxSize
    * @param autoCommit
    * @param driver
    * @param cachePreparedStatements
    * @param preparedStatementCacheSize
    * @param preparedStatementCacheLimit
    * @param useServerPrepareStatement
    * @param idleTimeout
    * @param maxLifetime
    * @param characterEncoding
    * @param useUnicode
    * @param leakDetectionThreshold
    */
   DBConfig(ConnectionPoolType connectionPoolType, DBProvider databaseProvider, String user, String password,
         String databaseURI, int minimumPoolSize, int maximumPoolSize, boolean autoCommit, String driver,
         boolean cachePreparedStatements, int preparedStatementCacheSize, int preparedStatementCacheLimit,
         boolean useServerPrepareStatement, int idleTimeout, int maxLifetime, String characterEncoding,
         boolean useUnicode, int leakDetectionThreshold) {
      this.connectionPoolType = connectionPoolType;
      this.databaseProvider = databaseProvider;
      this.user = user;
      this.password = password;
      this.databaseURI = databaseURI;
      this.minimumPoolSize = minimumPoolSize;
      this.maximumPoolSize = maximumPoolSize;
      this.autoCommit = autoCommit;
      this.driver = driver;
      this.cachePreparedStatements = cachePreparedStatements;
      this.preparedStatementCacheSize = preparedStatementCacheSize;
      this.preparedStatementCacheLimit = preparedStatementCacheLimit;
      this.useServerPrepareStatement = useServerPrepareStatement;
      this.idleTimeout = idleTimeout;
      this.maxLifetime = maxLifetime;
      this.characterEncoding = characterEncoding;
      this.useUnicode = useUnicode;
      this.leakDetectionThreshold = leakDetectionThreshold;
   }
   protected static DBConfig fdi_dev = DBConfigBuilder.getBuilder().
         setDatabaseURI("jdbc:mysql://fdi-dev.cs0o93i288pv.us-east-1.rds.amazonaws.com:3306/fdb").
         setUser("root").
         setPassword("SWAqu3ef").
         build();
}
