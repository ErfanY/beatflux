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

import com.beatflux.api.SpotAPI;
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
   @Path("/add")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response addSpot(String json) {
      Response response = null;
      try {
         Gson gson = new GsonBuilder().create();
         SpotAPI api = new SpotAPI();
         Spot s = gson.fromJson(json, Spot.class);
         api.addSpot(s);
         if(api.checkUser(s.getSpotID())){
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
   public Response updateSpot(String json) {
      Response response = null;
      try {
         Gson gson = new GsonBuilder().create();
         SpotAPI api = new SpotAPI();
         Spot s = gson.fromJson(json, Spot.class);
         api.updateSpot(s);
         if (api.checkUser(s.getSpotID())) {
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
