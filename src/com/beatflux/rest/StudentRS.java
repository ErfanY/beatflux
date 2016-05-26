package com.beatflux.rest;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonFactory;
import org.json.JSONObject;

import com.beatflux.db.StudentDAL;
import com.beatflux.objects.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/student")
public class StudentRS {
	@GET
	@Path("/list")
	@Produces("application/json")
	public Response list() {
		Response response = null;
		try {
			StudentDAL dal = new StudentDAL();
			List<Student> s = dal.listStudents(); 
			response = Response.ok(s).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			response = Response.serverError().build();
		}
		return response;
	}
	
	@GET
	@Path("/query")
	public Response query (
			@QueryParam("personalnumber") String pn) {
		Objects.requireNonNull(pn);
		StudentDAL sd = new StudentDAL();
		Student s = sd.getStudent(pn);
		return Response.ok(s).type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/del")
	public Response delete (
			@QueryParam("student_id") int id) {
		Objects.requireNonNull(id);
		StudentDAL sd = new StudentDAL();
		sd.deleteStudent(id);
		return Response.ok().type(MediaType.APPLICATION_JSON).build();
	}
	
	@POST	
	@Path("/registertest")
	public Response register(@FormParam("name") String name) {
		Response response = null;
		String text = name + " is registered now";
		response = Response.ok(text).build();
		return response;
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add (String json) {
		try {
			Gson gson = new GsonBuilder().create();
			StudentDAL sd = new StudentDAL();
			Student s = gson.fromJson(json, Student.class);
			sd.addStudent(s);
		} catch(Exception e) {
			Response.serverError().build();
		}
		return Response.status(201).entity(json).build();
	}
	
	@POST
	@Path("/upd")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update (String json) {
		try {
			Gson gson = new GsonBuilder().create();
			StudentDAL sd = new StudentDAL();
			Student s = gson.fromJson(json, Student.class);
			sd.updateStudent(s);
		} catch(Exception e) {
			Response.serverError().build();
		}
		return Response.status(201).entity(json).build();
	}
}
