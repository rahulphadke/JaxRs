package jaxrs01.service;

import java.util.ArrayList;
import java.util.List;

import jaxrs01.model.Employee;

public class EmpDb {
	private static List<Employee> empList = new ArrayList<Employee>();
	static
	{
		System.out.println("**** EmpDb::static block : initilizing empList ****");
		empList.add(new Employee(1, "Abc", 10000));
		empList.add(new Employee(2, "Pqr", 20000));
		empList.add(new Employee(3, "Akshay Kumar", 80000));
	}
	public static List<Employee> getEmpList() {
		return empList;
	}
	/*
	 * public static void setEmpList(List<Employee> empList) { EmpList.empList =
	 * empList; }
	 */	

}
