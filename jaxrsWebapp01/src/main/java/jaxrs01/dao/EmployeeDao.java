package jaxrs01.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jaxrs01.model.Employee;

public class EmployeeDao {
	public EmployeeDao() {
		System.out.println("EmployeeDao()");
	}
	
	public List<Employee> getAllEmps() {

		Connection con = connectToDb();
		Employee e = null;
		List<Employee> list=new ArrayList<Employee>();
		try {
			PreparedStatement pst = con.prepareStatement("select * from emp");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int empno = rs.getInt(1);
				String name = rs.getString(2);
				float salary = rs.getFloat(3);
				
				e = new Employee(empno, name, salary);
				list.add(e);
			}
			System.out.printf("\n got all emps...now returning the list");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	
	public String save(Employee e){
		String result=null;
		Connection con = connectToDb();
		try {
			PreparedStatement pst = con.prepareStatement("Insert into emp values (?,?,?)");
			pst.setInt(1, e.getEmpno());
			pst.setString(2, e.getName());
			pst.setFloat(3, e.getSalary());
			
			int cnt= pst.executeUpdate();
			System.out.println(cnt+" records updated");
			result=cnt+" records updated";
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			result="Some error occoured in db work";
		}
		return result;
	}
	public Employee getEmpById(int id) {
		// search for empno in db and return corresponding obj 
		Connection con = connectToDb();
		Employee found = null;

		try {
			PreparedStatement pst = con.prepareStatement("select * from emp where empno=?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int empno = rs.getInt(1);
				String name = rs.getString(2);
				float salary = rs.getFloat(3);
				System.out.printf("\n %d %s %f", empno, name, salary);
				found = new Employee(empno, name, salary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return found;
		
	}
	
	public String deleteEmpById(int id) {
		// search for empno in db and delete from the table
		Connection con = connectToDb();
		String result=null;

		try {
			PreparedStatement pst = con.prepareStatement("Delete from emp where empno=?");
			pst.setInt(1, id);
			int cnt= pst.executeUpdate();
			result = cnt+" records deleted";
		} catch (SQLException e) {
			e.printStackTrace();
			result = "Some error occoured in db work";
		}
		
		return result;
		
	}

	
	public static Connection connectToDb() {
		String driverClass = "com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://localhost:3306/expleo";
		String username = "root";
		String password = "root123";

		Connection con = null;
		try {
			Class.forName(driverClass);
			System.out.println("1. Db Driver loaded");
			con = DriverManager.getConnection(dbUrl, username, password);
			System.out.println("2. Got connection = " + con);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
}
