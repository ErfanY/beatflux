package com.beatflux.rest.api;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getUser(@CookieParam(AppConfig.APP_COOKIE_SECRET) String cookie) {
      long id = new CookieAPI().validateCookie(cookie);
      User user = new UserAPI().getUser(id);
      return Response.ok().entity(user).type(MediaType.APPLICATION_JSON).build();
   }
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
   @POST
   @Path("/delete")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED )
   public Response deleteUser(@FormParam("email") String email) {
      Response response = null;
      try {
         UserAPI api = new UserAPI();
         if (api.emailExist(email)) {
        	 api.deleteUser(email);
             response = Response.ok().entity("record is deleted").build();
         } else {
        	 response = Response.ok().entity("email does not exist!").build();
         }
         
      } catch (Exception e) {
         logger.warn("Failed to delete user", e);
         response = Response.serverError().build();
      }
      return response;
   }
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
	   Response response = null;
	   UserAPI api = new UserAPI();
	   User user = new User();
	   try {
		   if (api.emailExist(email)) {
			   response = Response.ok().entity("Email already exist!").build();
		   } else {
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
			   java.util.Date date = sdf.parse(birthdate);
			   java.sql.Date sqlDate = new Date(date.getTime());
			   if (!api.isValidEmail(email)) {
				   response = Response.status(Status.NOT_ACCEPTABLE).entity("invalid email address!").build();
			   } else {
				   user.setUserName(username);
				   user.setFirstName(firstname);
				   user.setLastName(lastname);
				   user.setCountryCode(country_code);
				   user.setBirthDate(sqlDate);
				   user.setEmail(email);
				   user.setMobileNumber(mobile_number);
				   api.addUser(user, password);
				   response = Response.ok().entity("Congrats! You are registered :)").build();
			   }
		   }
	   } catch (Exception e) {
		   logger.warn("Failed to sign up user", e);
		   response = Response.serverError().build();
	   }
	   return response;
   }
   @POST
   @Path("/login")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED )
   public Response loginUser(@FormParam("email") String email, @FormParam("password") String password) {
      Response response = null;
      UserAPI api = new UserAPI();
      CookieAPI cookieApi = new CookieAPI();
      try {
         // Authenticate against database
         if (api.userExist(email, password))
          {
            long userId = api.getUser(email).getUserID(); // Replace with getUser by email
            String cookie_value = cookieApi.randomString(60);
            Cookie cookie = new Cookie(AppConfig.APP_COOKIE_SECRET, cookie_value);
            int cookieAge = (int)TimeUnit.MINUTES.toSeconds(10);
            Timestamp expiryTimestamp = new Timestamp(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(cookieAge));
            NewCookie nc = new NewCookie(cookie, "This is our first cookie", cookieAge, false);
            cookieApi.addCookie(userId, cookie_value, expiryTimestamp);
            response = Response.ok().cookie(nc).entity("Congrats").build();
          } else {
            response = Response.status(Status.NOT_ACCEPTABLE).entity("invalid email or password!").build();
          }
      } catch (Exception e) {
         logger.warn("Failed to login user", e);
         response = Response.serverError().build();
      }
      return response;
   }
   @GET
   @Path("/logout")
   @Produces(MediaType.APPLICATION_JSON)
   public Response checkUserCookie(@CookieParam(value=AppConfig.APP_COOKIE_SECRET) String secret){
      CookieAPI cap = new CookieAPI();
      cap.validateCookie(secret);
      cap.deleteCookie(secret);
      return Response.ok().build(); 
   }
}

