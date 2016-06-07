package com.beatflux.rest;

import java.sql.Connection;
import java.sql.SQLException;

import com.beatflux.db.common.ConnectionManager;

public class Hello {

   public static void main(String[] args) throws SQLException {
      Connection c = ConnectionManager.getConnection();
   }

}
