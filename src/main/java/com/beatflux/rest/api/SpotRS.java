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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beatflux.api.CookieAPI;
import com.beatflux.api.SpotAPI;
import com.beatflux.api.UserAPI;
import com.beatflux.common.AppConfig;
import com.beatflux.rest.objects.Spot;

@Path("/spot")
public class SpotRS {
   private static final Logger logger = LoggerFactory.getLogger(UserRS.class);
   @GET
   @Path("/list")
   @Produces(MediaType.APPLICATION_JSON)
   public Response listSpot() {
      Response response = null;
      try {
         SpotAPI api = new SpotAPI();
         List<Spot> s = api.getSpotList();
         response = Response.ok(s).type(MediaType.APPLICATION_JSON).build();
      } catch (Exception e) {
         logger.warn("Failed to list spots.", e);
         response = Response.serverError().build();
      }
      return response;
   }
   @GET
   @Path("/search/{spot_id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response searchSpot(@PathParam("spot_id") int id) {
      Objects.requireNonNull(id);
      Response response = null;
      try {
         SpotAPI api = new SpotAPI();
         Spot s = api.searchSpot(id);
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
   public Response deleteSpot(@QueryParam("spot_id") int id) {
      Objects.requireNonNull(id);
      Response response = null;
      try {
         SpotAPI api = new SpotAPI();
         api.deleteSpot(id);
         response = Response.ok().type(MediaType.APPLICATION_JSON).build();
      } catch (Exception e) {
         e.printStackTrace();
         response = Response.serverError().build();
      }
      return response;
   }
   
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
      Response response = null;
      SpotAPI api = new SpotAPI();
      Spot spot = new Spot();
      try {
         if (api.emailExist(email)) {
            response = Response.ok().entity("Email already exist!").build();
         } else {
            if (!api.isValidEmail(email)) {
               response = Response.status(Status.NOT_ACCEPTABLE).entity("invalid email address!").build();
            } else {
               spot.setUserName(username);
               spot.setName(name);
               spot.setEquipment(equipment);
               spot.setCountryCode(country_code);
               spot.setEmail(email);
               spot.setPhoneNumber(phone_number);
               api.addSpot(spot, password);
               response = Response.ok().entity("Congrats! You are registered :)").build();
            }
         }
      } catch (Exception e) {
         logger.warn("Failed to sign up spot place!", e);
         response = Response.serverError().build();
      }
      return response;
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
      Response response = null;
      SpotAPI api = new SpotAPI();
      Spot spot = new Spot();
      try {
         if (!api.emailExist(email)) {
            response = Response.ok().entity("Email does not exist!").build();
         } else {
            if (!api.isValidEmail(email)) {
               response = Response.status(Status.NOT_ACCEPTABLE).entity("invalid email address!").build();
            } else {
               spot.setUserName(username);
               spot.setName(name);
               spot.setEquipment(equipment);
               spot.setCountryCode(country_code);
               spot.setEmail(email);
               spot.setPhoneNumber(phone_number);
               api.updateSpot(spot, password);
               response = Response.ok().entity("Congrats! You are registered :)").build();
            }
         }
      } catch (Exception e) {
         logger.warn("Failed to sign up spot place!", e);
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
            response = Response.ok().cookie(nc).entity("Congrats").build();
          } else {
            response = Response.status(Status.NOT_ACCEPTABLE).entity("invalid email or password!").build();
          }
      } catch (Exception e) {
         logger.warn("Failed to login spot", e);
         response = Response.serverError().build();
      }
      return response;
   }
   
   @GET
   @Path("/logout")
   @Produces(MediaType.APPLICATION_JSON)
   public Response checkSpotCookie(@CookieParam(value=AppConfig.APP_COOKIE_SECRET) String secret){
      CookieAPI cap = new CookieAPI();
      cap.validateCookie(secret);
      cap.deleteCookie(secret);
      return Response.ok().build(); 
   }
   
}
