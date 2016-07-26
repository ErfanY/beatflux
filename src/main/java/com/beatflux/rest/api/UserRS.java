package com.beatflux.rest.api;

import java.sql.Date;
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
      try {
         // Authenticate against database
         if (api.userExist(email, password))
          {
            Cookie cookie = new Cookie("secret", "beatfluxverysecretcookie");
            int cookieAge = (int)TimeUnit.MINUTES.toSeconds(2);
            NewCookie nc = new NewCookie(cookie, "This is our first cookie", cookieAge, false);
            response = Response.ok().cookie(nc).entity("Congrats").build();
          } else {
            response = Response.status(Status.NOT_ACCEPTABLE).entity("invalid email or password!").build();
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

