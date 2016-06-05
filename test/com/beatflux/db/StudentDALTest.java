package com.beatflux.db;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.beatflux.objects.Student;

public class StudentDALTest {
	StudentDAL dal = new StudentDAL();
	Student student = new Student();
	
	@Test
	public void testList(){
		List<Student> studentList = dal.listStudents();
		assertFalse(studentList.isEmpty());
		assertFalse(studentList.size()== 1);		
	}
	
	@Test
	public void testAddAndDelete(){
		List<Student> studentList = dal.listStudents();
		int size = studentList.size();
		student.setId(size+1);
		student.setName("this is test");
		student.setLastName("test");
		student.setPN("1234567890");
		dal.AddStudent(student);
		studentList = dal.listStudents();
		assertFalse(studentList.isEmpty());
		assertTrue(studentList.size() == size+1);
		dal.DeleteStudent(size+1);
		studentList = dal.listStudents();
		assertTrue(studentList.size() == size);	
	}
}
