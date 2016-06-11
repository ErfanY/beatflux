package com.beatflux.db.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
   /**
    * Close connection safely. null acceptable.
    * @param c -  connection
    */
   public static void safeClose(Connection c) {
      if (c != null) {
         try { c.close(); } catch (SQLException e) {}
      }
   }

   /**
    * Close statement safely. null acceptable.
    * @param s - Statement
    */
   public static void safeClose(Statement s) {
      if (s != null) {
         try { s.close(); } catch (SQLException e) {}
      }
   }

   /**
    * Close preparedstatement safely. null acceptable.
    * @param ps - PreparedStatement
    */
   public static void safeClose(PreparedStatement ps) {
      if (ps != null) {
         try { ps.close(); } catch (SQLException e) {}
      }
   }

   /**
    * Close resultset safely. null acceptable.
    * @param rs - ResultSet
    */
   public static void safeClose(ResultSet rs) {
      if (rs != null) {
         try { rs.close(); } catch (SQLException e) {}
      }
   }
}
