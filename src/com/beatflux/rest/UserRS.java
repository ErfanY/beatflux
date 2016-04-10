package com.beatflux.rest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/saveUser")
public class UserRS {
	private String filePath;

	@GET
	public Response getUser(
			@QueryParam("firstname") String firstName,
			@QueryParam("lastname") String lastName,
			@QueryParam("pn") String persNo) {
		try  {
			String content ="firstname:" + firstName + ",lastname:" + lastName + ",pn:" + persNo;
			filePath = System.getProperty("user.home");
			File file = new File(filePath, "user.txt");
			
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.newLine();
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return Response.ok("The file is saved at: " + filePath).build();
	}
}
