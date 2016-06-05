package com.beatflux.rest;


import java.util.List;
import java.util.Objects;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import com.beatflux.db.StudentDAL;
import com.beatflux.objects.Student;

@Path("/student")
public class StudentRS {
	@GET
	@Path("/list")
	@Produces("application/json")
	public Response listStudents(){
		Response response = null;
		try{
			StudentDAL dal = new StudentDAL();
			List<Student> s = dal.listStudents();
			response = Response.ok(s).type(MediaType.APPLICATION_JSON).build();
		}catch(Exception e) {
			e.printStackTrace();
			response = Response.serverError().build();
		}
		return response;
	}

	@GET
	@Path("/search")
	@Produces("application/json")
	public Response searchStudents(@QueryParam("personalnumber") String pn) {
		Objects.requireNonNull(pn);
		Response response = null;
		try{
			StudentDAL dal = new StudentDAL();
			Student s = dal.SearchStudentsbyPersonalNumber(pn);
			response = Response.ok(s).type(MediaType.APPLICATION_JSON).build();
		}catch(Exception e) {
			e.printStackTrace();
			response = Response.serverError().build();
		}
		return response;
	}
	
	@GET
	@Path("/delete")
	@Produces("application/json")
	public Response searchStudents(@QueryParam("student_id") int id){
		Objects.requireNonNull(id);
		Response response = null;
		try{
			StudentDAL dal = new StudentDAL();
			Student s = dal.DeleteStudent(id); 
			response = Response.ok(s).type(MediaType.APPLICATION_JSON).build();
		}catch(Exception e) {
			e.printStackTrace();
			response = Response.serverError().build();
		}
		return response;
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addStudents(String json){
		Response response = null;
		try{
			Gson gson = new GsonBuilder().create();
			StudentDAL dal = new StudentDAL();
			Student s = gson.fromJson(json, Student.class);
			dal.AddStudent(s);
			if(dal.CheckStudentsbyPersonalNumberAndID(s.getPN(), s.getId())){
				response = Response.status(HttpStatus.SC_CONFLICT).build();
			} else {
				response = Response.status(HttpStatus.SC_CREATED).build();
			}
		}catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return response;
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateStudents(String json){
		Response response = null;
		try{
			Gson gson = new GsonBuilder().create();
			StudentDAL dal = new StudentDAL();
			Student s = gson.fromJson(json, Student.class);
			dal.UpdateStudent(s);
			if(dal.CheckStudentsbyPersonalNumberAndID(s.getPN(), s.getId())){
				response = Response.status(HttpStatus.SC_ACCEPTED).build();
			} else {
				response = Response.status(HttpStatus.SC_NOT_FOUND).build();
			}
		}catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return response;
	}
}
