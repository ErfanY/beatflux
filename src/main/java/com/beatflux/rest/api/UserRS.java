package com.beatflux.rest.api;

import java.util.List;
import java.util.Objects;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beatflux.api.UserAPI;
import com.beatflux.rest.objects.User;

@Path("/user")
public class UserRS {
   private static final Logger logger = LoggerFactory.getLogger(UserRS.class);
   @GET
   @Path("/list")
   @Produces(MediaType.APPLICATION_JSON)
   public Response listUser() {
      Response response = null;
      try {
         UserAPI api = new UserAPI();
         List<User> s = api.getUserList();
         response = Response.ok(s).type(MediaType.APPLICATION_JSON).build();
      } catch (Exception e) {
         logger.warn("Failed to list users", e);
         response = Response.serverError().build();
      }
      return response;
   }

   @GET
   @Path("/search/{user_id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response searchUser(@PathParam("user_id") int id) {
      Objects.requireNonNull(id);
      Response response = null;
      try {
         UserAPI api = new UserAPI();
         User s = api.searchUser(id);
         response = Response.ok(s).type(MediaType.APPLICATION_JSON).build();
      } catch (Exception e) {
         e.printStackTrace();
         response = Response.serverError().build();
      }
      return response;
   }

   @GET
   @Path("/delete")
   @Produces(MediaType.APPLICATION_JSON)
   public Response deleteUser(@QueryParam("user_id") int id) {
      Objects.requireNonNull(id);
      Response response = null;
      try {
         UserAPI api = new UserAPI();
         api.deleteUser(id);
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
         UserAPI api = new UserAPI();
         User s = gson.fromJson(json, User.class);
         api.addUser(s);
         if(api.checkUser(s.getUserID())){
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
         UserAPI api = new UserAPI();
         User s = gson.fromJson(json, User.class);
         api.updateUser(s);
         if (api.checkUser(s.getUserID())) {
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
