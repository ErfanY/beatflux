package com.beatflux.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloRS {
   @GET
   public Response greet(@QueryParam("name") String name) {
      if (name == null)
         name = "Stranger";
      return Response.ok("Hello " + name + "!").build();
   }
}
