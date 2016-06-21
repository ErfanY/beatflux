/*package com.beatflux.db.dal;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import com.beatflux.objects.Student;

public class StudentDALTest {
	StudentDAL dal = new StudentDAL();
	Student student = new Student();
	
	@Test 
	public void testListStudents() {
		List<Student> studentList = dal.listStudents();
		Student s = studentList.get(0);
		assertFalse(studentList.isEmpty());
		assertFalse(studentList.size() == 1);
		assertTrue(s.getFirstName().equals("nahid"));
	}
	
	@Test
	public void testQuery() {
		assertNotNull("object should exist", dal.getByPN("1985092119"));
	}
	
	@Test
	public void testAdd() {
		student.setStudent_id(6);
		student.setFirstName("Sara");
		student.setLastName("Dolkhani");
		student.setPersonalNumber("6789101");
		
		dal.addStudent(student);
		assertNotNull(dal.getByID(6));
	}
	
	@Test
	public void testUpdate() {
		student.setStudent_id(6);
		student.setFirstName("Nasim");
		student.setLastName("Seidi");
		student.setPersonalNumber("6789101");
		
		dal.updateStudent(student);
		assertNotNull("object should exist", dal.getByID(6));
	}
	
	@Test
	public void testDelete() {
		dal.deleteStudent(7);
		assertNull("object should not exist", dal.getByID(7));
		
	}
	
}*/