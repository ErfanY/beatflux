package com.beatflux.rest.api;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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

import com.beatflux.api.CookieAPI;
import com.beatflux.api.UserAPI;
import com.beatflux.common.AppConfig;
import com.beatflux.rest.objects.User;

@Path("/user")
public class UserRS {
   private static final Logger logger = LoggerFactory.getLogger(UserRS.class);
   @POST
   @Path("/signup")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public Response addUser(
         @FormParam("username") String username,
         @FormParam("firstname") String firstname,
         @FormParam("lastname") String lastname,
         @FormParam("password") String password,
         @FormParam("country_code") String country_code,
         @FormParam("birthdate") String birthdate,
         @FormParam("email") String email,
         @FormParam("mobile_number") String mobile_number) {
      UserAPI api = new UserAPI();
      User user = new User();
      try {
         if (api.emailExist(email)) {
            return Response.ok().entity("Email already exist!").build();
         } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
            java.util.Date date = sdf.parse(birthdate);
            java.sql.Date sqlDate = new Date(date.getTime());
            if (!api.isValidEmail(email)) {
               return Response.status(Status.NOT_ACCEPTABLE).entity("invalid email address!").build();
            } else {
               user.setUserName(username);
               user.setFirstName(firstname);
               user.setLastName(lastname);
               user.setCountryCode(country_code);
               user.setBirthDate(sqlDate);
               user.setEmail(email);
               user.setMobileNumber(mobile_number);
               api.addUser(user, password);
               return Response.ok().entity("Congrats! You are registered :)").build();
            }
         }
      } catch (Exception e) {
         logger.warn("Failed to sign up user", e);
         return Response.serverError().build();
      }
   }
   @GET
   @Path("/list")
   @Produces(MediaType.APPLICATION_JSON)
   public Response listUser() {
      try {
         UserAPI api = new UserAPI();
         List<User> users = api.listUser();
         if (users.size() == 0) {
            return Response.ok().entity("Users list is empty!").build();
         } else {
            return Response.ok(users).type(MediaType.APPLICATION_JSON).build();
         }
      } catch (Exception e) {
         logger.warn("Failed to list users", e);
         return Response.serverError().build();
      }
   }
   @POST
   @Path("/update")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public Response updateUser(
         @FormParam("username") String username,
         @FormParam("firstname") String firstname,
         @FormParam("lastname") String lastname,
         @FormParam("password") String password,
         @FormParam("country_code") String country_code,
         @FormParam("birthdate") String birthdate,
         @FormParam("email") String email,
         @FormParam("mobile_number") String mobile_number) {
      UserAPI api = new UserAPI();
      User user = new User();
      try {
         if (!api.emailExist(email)) {
            return Response.ok().entity("Email does not exist!").build();
         } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            java.util.Date date = sdf.parse(birthdate);
            java.sql.Date sqlDate = new Date(date.getTime());
            user.setUserName(username);
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setCountryCode(country_code);
            user.setBirthDate(sqlDate);
            user.setEmail(email);
            user.setMobileNumber(mobile_number);
            api.updateUser(user, password);
            return Response.ok().entity("User is updated!").build();
         }
      } catch (Exception e) {
         logger.warn("Failed to update user!", e);
         return Response.serverError().build();
      }
   }
   @POST
   @Path("/delete")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED )
   public Response deleteUser(@FormParam("email") String email) {
      try {
         UserAPI api = new UserAPI();
         if (api.emailExist(email)) {
            api.deleteUser(email);
            return Response.ok().entity("record is deleted").build();
         } else {
            return Response.ok().entity("email does not exist!").build();
         }
      } catch (Exception e) {
         logger.warn("Failed to delete user", e);
         return Response.serverError().build();
      }
   }
   @GET
   @Path("/search/{user_id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response searchUser(@PathParam("user_id") int id) {
      Objects.requireNonNull(id);
      try {
         UserAPI api = new UserAPI();
         User user = api.searchUser(id);
         if (user == null) {
            return Response.ok().entity("User doesn't exist!").build();
         } else {
            return Response.ok().entity(user).type(MediaType.APPLICATION_JSON).build();
         }
      } catch (Exception e) {
         logger.warn("Failed to search user!", e);
         return Response.serverError().build();
      }
   }
   @POST
   @Path("/login")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED )
   public Response loginUser(@FormParam("email") String email, @FormParam("password") String password) {
      UserAPI api = new UserAPI();
      CookieAPI cookieApi = new CookieAPI();
      try {
         // Authenticate against database
         if (api.userExist(email, password))
         {
            long userId = api.getUser(email).getUserID(); 
            String cookie_value = cookieApi.randomString(60);
            Cookie cookie = new Cookie(AppConfig.APP_COOKIE_SECRET, cookie_value);
            int cookieAge = (int)TimeUnit.MINUTES.toSeconds(10);
            Timestamp expiryTimestamp = new Timestamp(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(cookieAge));
            NewCookie nc = new NewCookie(cookie, "This is our first cookie", cookieAge, false);
            cookieApi.addCookie(userId, cookie_value, expiryTimestamp);
            return Response.ok().cookie(nc).entity("You're logged in :)").build();
         } else {
            return Response.status(Status.NOT_ACCEPTABLE).entity("invalid email or password!").build();
         }
      } catch (Exception e) {
         logger.warn("Failed to login user", e);
         return Response.serverError().build();
      }
   }
   @GET
   @Path("/logout")
   @Produces(MediaType.APPLICATION_JSON)
   public Response logoutUser(@CookieParam(value=AppConfig.APP_COOKIE_SECRET) String secret){
      CookieAPI cap = new CookieAPI();
      try {
         cap.validateCookie(secret);
         cap.deleteCookie(secret);
         if (secret == null) {
            return Response.ok().entity("You're not logged in yet!").build();
         }
         return Response.ok().entity("You're logged out successfully!").build(); 
      } catch (Exception e) {
         logger.warn("Failed to logout user", e);
         return Response.serverError().build();
      }
   }
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getUser(@CookieParam(AppConfig.APP_COOKIE_SECRET) String cookie) {
      try {
         long id = new CookieAPI().validateCookie(cookie);
         User user = new UserAPI().getUser(id);
         if (user == null) {
            return Response.ok().entity("Please login first!").build();
         } else {
            return Response.ok().entity(user).type(MediaType.APPLICATION_JSON).build();
         }
      } catch (Exception e) {
         logger.warn("Failed to get user", e);
         return Response.serverError().build();
      }
   }
}
