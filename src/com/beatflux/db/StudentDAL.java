package com.beatflux.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beatflux.objects.Student;

public class StudentDAL {

	public StudentDAL() {
	}
	public List<Student> listStudents(){

		String sql = "SELECT * FROM students";
		List<Student> db = new ArrayList<Student>();
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection conns = null;
		try {
			conns = ConnectionManager.connection();
			statement = conns.prepareStatement(sql);
			result = statement.executeQuery();

			while (result.next()){
				Student student = new Student();
				student.setId(result.getInt("student_id"));
				student.setName(result.getString("firstname"));
				student.setLastName(result.getString("lastname"));
				student.setPN(result.getString("personalnumber"));
				student.setRegistertimestamp(result.getTimestamp("registertimestamp"));
				db.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				result.close();
				statement.close();
				conns.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return db;	

	}

	public Student SearchStudentsbyPersonalNumber(String pn){

		String sql = "SELECT * FROM students WHERE personalNumber=?";
		Student student = new Student();
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection conns = null;
		try {
			conns = ConnectionManager.connection();
			statement = conns.prepareStatement(sql);
			statement.setString(1, pn);
			result = statement.executeQuery();

			while (result.next()){
				student.setId(result.getInt("student_id"));
				student.setName(result.getString("firstname"));
				student.setLastName(result.getString("lastname"));
				student.setPN(result.getString("personalnumber"));
				student.setRegistertimestamp(result.getTimestamp("registertimestamp"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				result.close();
				statement.close();
				conns.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return student;	

	}


	public Student DeleteStudent(int id){

		String sql = "DELETE FROM students WHERE student_id=?";
		Student student = new Student();
		PreparedStatement statement = null;
		Connection conns = null;
		try {
			conns = ConnectionManager.connection();
			statement = conns.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
				conns.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return student;	

	}


	public Student AddStudent(Student newStudent){

		String sql = "INSERT INTO students (student_id, firstname, lastname, personalnumber) "
				+ "VALUES (?,?,?,?)";
		PreparedStatement statement = null;
		Connection conns = null;
		try {
			conns = ConnectionManager.connection();
			statement = conns.prepareStatement(sql);
			statement.setInt(1,newStudent.getId());
			statement.setString(2, newStudent.getFirstName());
			statement.setString(3, newStudent.getLastName());
			statement.setString(4, newStudent.getPN());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
				conns.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return newStudent;	

	}

	public boolean CheckStudentsbyPersonalNumberAndID(String pn, int id){

		String sql = "SELECT * FROM students WHERE personalNumber=? || student_id=?";
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection conns = null;
		try {
			conns = ConnectionManager.connection();
			statement = conns.prepareStatement(sql);
			statement.setString(1, pn);
			statement.setInt(2, id);
			result = statement.executeQuery();
			if (result.next()){
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				result.close();
				statement.close();
				conns.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;	

	}

	public Student UpdateStudent(Student newStudent){

		String sql = "UPDATE students SET firstname=?, lastname = ?, personalnumber=? WHERE student_id=?";
		PreparedStatement statement = null;
		Connection conns = null;
		try {
			conns = ConnectionManager.connection();
			statement = conns.prepareStatement(sql);
			statement.setString(1, newStudent.getFirstName());
			statement.setString(2, newStudent.getLastName());
			statement.setString(3, newStudent.getPN());
			statement.setInt(4,newStudent.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
				conns.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return newStudent;	
	}
}


