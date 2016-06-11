package com.beatflux.db.common;

/**
 * This class includes database configurations.
 */
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
    * Constructs a database configuration
    * @param connectionPoolType - Pool implementation
    * @param databaseProvider - Database provider
    * @param user - Username
    * @param password - Password
    * @param databaseURI - URI for database
    * @param poolMinSize - Minimum number of connections in pool
    * @param poolMaxSize - Maximum number of connections in pool
    * @param autoCommit - Automatic commit
    * @param driver - Database diver
    * @param cachePreparedStatements - Cache preparedstatements
    * @param preparedStatementCacheSize - Cache size for prepraredstatements
    * @param preparedStatementCacheLimit - Cache limit for preparedstatements
    * @param useServerPrepareStatement - Use server preparestatement
    * @param idleTimeout - Idle timeout for connection
    * @param maxLifetime - Maximum lifetime for configuration
    * @param characterEncoding - Character encoding for connection
    * @param useUnicode - Use unicode
    * @param leakDetectionThreshold - Leak detection threshold
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
   
   /**
    * DB configuration for fdi-dev instance in AWS
    */
   protected static DBConfig fdi_dev = DBConfigBuilder.getBuilder(true).
         setDatabaseURI("jdbc:mysql://fdi-dev.cs0o93i288pv.us-east-1.rds.amazonaws.com:3306/fdb").
         setUser("root").
         setPassword("SWAqu3ef").
         build();
}
