package com.beatflux.rest.api;

import java.sql.Timestamp;
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
import com.beatflux.api.SpotAPI;
import com.beatflux.common.AppConfig;
import com.beatflux.rest.objects.Spot;

@Path("/spot")
public class SpotRS {
   private static final Logger logger = LoggerFactory.getLogger(UserRS.class);
   @POST
   @Path("/signup")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public Response addSpot(
         @FormParam("username") String username,
         @FormParam("name") String name,
         @FormParam("equipment") String equipment,
         @FormParam("password") String password,
         @FormParam("country_code") String country_code,
         @FormParam("email") String email,
         @FormParam("phone_number") String phone_number) {
      SpotAPI api = new SpotAPI();
      Spot spot = new Spot();
      try {
         if (api.emailExist(email)) {
            return Response.ok().entity("Email already exist!").build();
         } else {
            if (!api.isValidEmail(email)) {
               return Response.status(Status.NOT_ACCEPTABLE).entity("invalid email address!").build();
            } else {
               spot.setUserName(username);
               spot.setName(name);
               spot.setEquipment(equipment);
               spot.setCountryCode(country_code);
               spot.setEmail(email);
               spot.setPhoneNumber(phone_number);
               api.addSpot(spot, password);
               return Response.ok().entity("Congrats! You are registered :)").build();
            }
         }
      } catch (Exception e) {
         logger.warn("Failed to sign up spot place!", e);
         return Response.serverError().build();
      }
   }
   @GET
   @Path("/list")
   @Produces(MediaType.APPLICATION_JSON)
   public Response listSpot() {
      try {
         SpotAPI api = new SpotAPI();
         List<Spot> spots = api.listSpot();
         if (spots.size() == 0) {
            return Response.ok().entity("Spots list is empty!").build();
         } else {
            return Response.ok(spots).type(MediaType.APPLICATION_JSON).build();
         }
      } catch (Exception e) {
         logger.warn("Failed to list spots.", e);
         return Response.serverError().build();
      }
   }
   @POST
   @Path("/update")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public Response updateSpot(
         @FormParam("username") String username,
         @FormParam("name") String name,
         @FormParam("equipment") String equipment,
         @FormParam("password") String password,
         @FormParam("country_code") String country_code,
         @FormParam("email") String email,
         @FormParam("phone_number") String phone_number) {
      SpotAPI api = new SpotAPI();
      Spot spot = new Spot();
      try {
         if (!api.emailExist(email)) {
            return Response.ok().entity("Email does not exist!").build();
         } else {
            if (!api.isValidEmail(email)) {
               return Response.status(Status.NOT_ACCEPTABLE).entity("invalid email address!").build();
            } else {
               spot.setUserName(username);
               spot.setName(name);
               spot.setEquipment(equipment);
               spot.setCountryCode(country_code);
               spot.setEmail(email);
               spot.setPhoneNumber(phone_number);
               api.updateSpot(spot, password);
               return Response.ok().entity("Congrats! You are registered :)").build();
            }
         }
      } catch (Exception e) {
         logger.warn("Failed to sign up spot place!", e);
         return Response.serverError().build();
      }
   }
   @POST
   @Path("/delete")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED )
   public Response deleteSpot(@FormParam("email") String email) {
      Objects.requireNonNull(email);
      try {
         SpotAPI api = new SpotAPI();
         api.deleteSpot(email);
         return Response.ok().type(MediaType.APPLICATION_JSON).build();
      } catch (Exception e) {
         e.printStackTrace();
         return Response.serverError().build();
      }
   }
   @GET
   @Path("/search/{spot_id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response searchSpot(@PathParam("spot_id") int id) {
      Objects.requireNonNull(id);
      try {
         SpotAPI api = new SpotAPI();
         Spot spot = api.searchSpot(id);
         if (spot == null) {
            return Response.ok().entity("Spot doesn't exist!").build();
         } else {
            return Response.ok().entity(spot).type(MediaType.APPLICATION_JSON).build();
         }
      } catch (Exception e) {
         logger.warn("Failed to search spot!", e);
         return Response.serverError().build();
      }
   }
   @POST
   @Path("/login")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED )
   public Response loginSpot(@FormParam("email") String email, @FormParam("password") String password) {
      SpotAPI api = new SpotAPI();
      CookieAPI cookieApi = new CookieAPI();
      try {
         // Authenticate against database
         if (api.spotExist(email, password))
         {
            long spotId = api.getSpot(email).getSpotID(); 
            String cookie_value = cookieApi.randomString(60);
            Cookie cookie = new Cookie(AppConfig.APP_COOKIE_SECRET, cookie_value);
            int cookieAge = (int)TimeUnit.MINUTES.toSeconds(10);
            Timestamp expiryTimestamp = new Timestamp(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(cookieAge));
            NewCookie nc = new NewCookie(cookie, "This is our first cookie", cookieAge, false);
            cookieApi.addCookie(spotId, cookie_value, expiryTimestamp);
            return Response.ok().cookie(nc).entity("Congrats").build();
         } else {
            return Response.status(Status.NOT_ACCEPTABLE).entity("invalid email or password!").build();
         }
      } catch (Exception e) {
         logger.warn("Failed to login spot", e);
         return Response.serverError().build();
      }
   }
   @GET
   @Path("/logout")
   @Produces(MediaType.APPLICATION_JSON)
   public Response logoutSpot(@CookieParam(value=AppConfig.APP_COOKIE_SECRET) String secret){
      CookieAPI cap = new CookieAPI();
      try {
         cap.validateCookie(secret);
         cap.deleteCookie(secret);
         if (secret == null) {
            return Response.ok().entity("You're not logged in yet!").build();
         }
         return Response.ok().entity("You're logged out successfully!").build(); 
      } catch (Exception e) {
         logger.warn("Failed to logout spot", e);
         return Response.serverError().build();
      } 
      
   }
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getSpot(@CookieParam(AppConfig.APP_COOKIE_SECRET) String cookie) {
      try {
         long id = new CookieAPI().validateCookie(cookie);
         Spot spot = new SpotAPI().searchSpot(id);
         if (spot == null) {
            return Response.ok().entity("Please login first!").build();
         } else {
            return Response.ok().entity(spot).type(MediaType.APPLICATION_JSON).build();
         }
      } catch (Exception e) {
         logger.warn("Failed to get user", e);
         return Response.serverError().build();
      }
   }
}
