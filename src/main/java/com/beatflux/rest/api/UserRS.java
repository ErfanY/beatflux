package com.beatflux.rest.api;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
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
         logger.warn("Failed to search user", e);
         response = Response.serverError().build();
      }
      return response;
   }
   @GET
   @Path("/delete/{user_id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response deleteUser(@PathParam("user_id") int id) {
      Objects.requireNonNull(id);
      Response response = null;
      try {
         UserAPI api = new UserAPI();
         api.deleteUser(id);
         response = Response.ok().type(MediaType.APPLICATION_JSON).build();
      } catch (Exception e) {
         logger.warn("Failed to delete user", e);
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
         UserAPI api = new UserAPI();
         User s = new User();
         //GsonBuilder gsonBuilder = new GsonBuilder();
        // gsonBuilder.setDateFormat("M/d/yy hh:mm a"); //Format of our JSON dates
         Gson gson = new GsonBuilder().create();
         s = gson.fromJson(json, User.class);
         Date date = new Date(System.currentTimeMillis());
         s.setBirthDate(date);
         api.addUser(s);
         if(api.checkUser(s.getUserID())){
            response = Response.status(Status.CONFLICT).build();
         } else {
            response = Response.status(Status.CREATED).build();
         }
      } catch (Exception e) {
         logger.warn("Failed to add user", e);
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
         logger.warn("Failed to update user", e);
         return Response.serverError().build();
      }
      return response;
   }
   
   @POST
   @Path("/login")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public Response authenticateUser(@FormParam("email") String email) {
      Response response = null;
      // Check password with hash and salt against database
      try {
         UserAPI api = new UserAPI();
         // Authenticate against database
         if (api.authenticateUser(email))
          {
            Cookie cookie = new Cookie("secret", "beatfluxverysecretcookie");
            int cookieAge = (int)TimeUnit.MINUTES.toSeconds(2);
            NewCookie nc = new NewCookie(cookie, "This is our first cookie", cookieAge, false);
            response = Response.ok().cookie(nc).entity("Congrats").build();
          } else {
            response = Response.status(Status.NOT_ACCEPTABLE).entity("shut up").build();
          }
      } catch (Exception e) {
         logger.warn("Failed to login user");
         response = Response.serverError().build();
      }
      return response;
   }
   
   @GET
   @Path("checkmycookie")
   public Response checkCookie(@CookieParam("secret") String secret) {
      Response r = null;
      if (secret.equals("beatfluxverysecretcookie"))
         r = Response.ok().entity("Congrats").build();
      else 
         r = Response.status(Status.NOT_ACCEPTABLE).entity("shut up").build();
      return r;
   }
   
}

