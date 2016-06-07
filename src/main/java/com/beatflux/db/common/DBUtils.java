package com.beatflux.db.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
   public static void safeClose(Connection c) {
      if (c != null) {
         try { c.close(); } catch (SQLException e) {}
      }
   }

   public static void safeClose(Statement s) {
      if (s != null) {
         try { s.close(); } catch (SQLException e) {}
      }
   }

   public static void safeClose(PreparedStatement p) {
      if (p != null) {
         try { p.close(); } catch (SQLException e) {}
      }
   }

   public static void safeClose(ResultSet p) {
      if (p != null) {
         try { p.close(); } catch (SQLException e) {}
      }
   }
}
