package jaxrs01.model;

// POJO : Plain Old Java Object
// Java bean
public class Employee {
	private int empno;
	private String name;
	private float salary;

	public Employee() {
		System.out.println("Employee()");
	}

	public Employee(int empno, String name, float salary) {
		System.out.println("Employee(***");
		this.empno = empno;
		this.name = name;
		this.salary = salary;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getName() {
		return name.toUpperCase();
	}
	public void setName(String name) {
		this.name = name.toUpperCase();
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

//	@Override
//	public String toString() {
//		return "Employee [empno=" + empno + ", name=" + name + ", salary=" + salary + "]";
//	}
	@Override
	public String toString() {
		return " [empno=" + empno + ", name=" + name + ", salary=" + salary + "]";
	}


}
