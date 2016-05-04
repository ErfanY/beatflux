package com.beatflux.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


@Path("/hello")
public class HelloRS {
	String sCurrentLine;
   @GET
   public Response greet(@QueryParam("name") String name, @QueryParam("lastName") String lastName,
		    @QueryParam("PN") String PN) {
      if (name == null && lastName == null)
         name = "Stranger";
      
      File file = new File("C:/Users/Nasrin/Documents/EclipseWorkSpace/beatflux/test1.txt");
      try {
			FileWriter fileWriter = new FileWriter(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileWriter.write("Hello "+ name + lastName + " !!!");
			fileWriter.write("PN is: " + PN);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
      
      try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/Nasrin/Documents/EclipseWorkSpace/beatflux/NasrinTest.txt")))
		{
			//String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
			 //System.out.println(sCurrentLine);
			 sCurrentLine.concat(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
      

      file = new File("D:/Document/Personbevis-New.pdf");
      try {
    	  FileInputStream fileInputStream = new FileInputStream(file);
    	  javax.ws.rs.core.Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response.ok((Object) fileInputStream);
    	  responseBuilder.type("application/pdf");
    	  responseBuilder.header("Content-Disposition", "filename=test.pdf");
    	  return responseBuilder.build();
      } catch (IOException e) {
    	  e.printStackTrace();
      }
      return Response.ok("Hello " + name + lastName + "!" + sCurrentLine).build();
   }
}
