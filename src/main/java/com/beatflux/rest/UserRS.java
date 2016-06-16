package com.beatflux.rest;

import java.util.List;
import java.util.Objects;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.beatflux.db.dal.UserDAL;
import com.beatflux.objects.User;

@Path("/user")
public class UserRS {
   @GET
   @Path("/list")
   @Produces("application/json")
   public Response listUser() {
      Response response = null;
      try {
         UserDAL dal = new UserDAL();
         List<User> s = dal.listUsers();
         response = Response.ok(s).type(MediaType.APPLICATION_JSON).build();
      } catch (Exception e) {
         e.printStackTrace();
         response = Response.serverError().build();
      }
      return response;
   }

   @GET
   @Path("/search")
   @Produces("application/json")
   public Response searchUser(@QueryParam("user_id") int id) {
      Objects.requireNonNull(id);
      Response response = null;
      try {
         UserDAL dal = new UserDAL();
         boolean s = dal.checkRecord(id);
         response = Response.ok(s).type(MediaType.APPLICATION_JSON).build();
      } catch (Exception e) {
         e.printStackTrace();
         response = Response.serverError().build();
      }
      return response;
   }

   @GET
   @Path("/delete")
   @Produces("application/json")
   public Response deleteUser(@QueryParam("user_id") int id) {
      Objects.requireNonNull(id);
      Response response = null;
      try {
         UserDAL dal = new UserDAL();
         dal.deleteUser(id);
         response = Response.ok().type(MediaType.APPLICATION_JSON).build();
      } catch (Exception e) {
         e.printStackTrace();
         response = Response.serverError().build();
      }
      return response;
   }

   @POST
   @Path("/add")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response addUser(String json) {
      Response response = null;
      try {
         Gson gson = new GsonBuilder().create();
         UserDAL dal = new UserDAL();
         User s = gson.fromJson(json, User.class);
         dal.addUser(s);
         if (dal.checkRecord(s.getUserID())) {
            response = Response.status(Status.CONFLICT).build();
         } else {
            response = Response.status(Status.CREATED).build();
         }
      } catch (Exception e) {
         e.printStackTrace();
         return Response.serverError().build();
      }
      return response;
   }

   @POST
   @Path("/update")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response updateUser(String json) {
      Response response = null;
      try {
         Gson gson = new GsonBuilder().create();
         UserDAL dal = new UserDAL();
         User s = gson.fromJson(json, User.class);
         dal.updateUser(s);
         if (dal.checkRecord(s.getUserID())) {
            response = Response.status(Status.ACCEPTED).build();
         } else {
            response = Response.status(Status.NOT_FOUND).build();
         }
      } catch (Exception e) {
         e.printStackTrace();
         return Response.serverError().build();
      }
      return response;
   }
}
