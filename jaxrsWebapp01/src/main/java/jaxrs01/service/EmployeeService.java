package jaxrs01.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jaxrs01.model.Employee;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

//Note : baseUri="http://localhost:8081/rest"
//as web.xml maps url pattern /rest

@Path("/employeeService")
//endpoint URL = ${baseUri}/employeeService
public class EmployeeService {
	private static List<Employee> empList = EmpDb.getEmpList();
	public EmployeeService() {
		System.out.println("EmployeeService()");
	}
	
	/*
	 * @OPTIONS
	 * 
	 * @Path("/save") public Response crossOrigin() {
	 * System.out.println("EmployeeService::crossOrigin()"); return Response.ok()
	 * .header("Access-Control-Allow-Origin", "*")
	 * .header("Access-Control-Allow-Credentials", "true")
	 * .header("Access-Control-Allow-Headers",
	 * "origin, content-type, accept, authorization")
	 * .header("Access-Control-Allow-Methods",
	 * "GET, POST, PUT, DELETE, OPTIONS, HEAD"). build(); }
	 */
	
	@POST
	@Path("/save") 		
	// endpoint URL = ${baseUri}/employeeService/save
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Employee e) {
		System.out.println("saveEmployee()");
		empList.add(e);
		System.out.println("Emp added : " + e);
		return Response.ok("Saved the emp")
				/*.header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Credentials", "true")
			      .header("Access-Control-Allow-Headers",
			        "origin, content-type, accept, authorization")
			      .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD")*/
				.build();
	}
	
	@GET
	@Path("/get/{id}")
	// endpoint URL = ${baseUri}/employeeService/get/3
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		System.out.println("getEmployee() id="+id);
		
		Employee found=null;
		
		for(Employee e:empList) {
			if(e.getEmpno()==id) {
				found=e;
				break;
			}
		}
		System.out.println("found="+found);
		return Response.ok(found)
				/*
				 * .header("Access-Control-Allow-Origin", "*")
				 * .header("Access-Control-Allow-Credentials", "true")
				 * .header("Access-Control-Allow-Headers",
				 * "origin, content-type, accept, authorization")
				 * .header("Access-Control-Allow-Methods",
				 * "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				 */
				.build();
	}
	
	@GET
	// endpoint URL = ${baseUri}/employeeService
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		System.out.println("** getAllEmployees()");
		System.out.println(empList);
		return Response.ok(empList)
//				.header("Access-Control-Allow-Origin", "*")
//				.header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
//				.header("Access-Control-Allow-Methods","GET, OPTIONS, HEAD")
				.build();
	}

	@DELETE
	@Path("/delete/{id}") 
	// endpoint URL = ${baseUri}/employeeService/delete/100
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) {
		System.out.println(" deleteEmployee()");
		String res = null;
		
		for (Iterator<Employee> itr = empList.iterator(); itr.hasNext();) {
			Employee e= itr.next();
			if(e.getEmpno()==id) {
				itr.remove();
				res="removed empno "+id;
				break;
			}
		}
		if(res==null)
			res="No emp found with id="+id;
		
		System.out.println(res);
		return Response.ok(res).build();
		
	}


	@PUT
	@Path("/update") 		
	// endpoint URL = ${baseUri}/employeeService/update
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Employee e) {
		System.out.println("update()");
		String res = null;
		int id=e.getEmpno();
		for (Iterator<Employee> itr = empList.iterator(); itr.hasNext();) {
			Employee t= itr.next();
			if(t.getEmpno()==id) {
				t.setName(e.getName());
				t.setSalary(e.getSalary());
				res="Updated "+e;
			}
		}
		if(res==null)
			res="No emp found with id="+id;
			
		System.out.println(res);
		
		return Response.ok(res)
				/*.header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Credentials", "true")
			      .header("Access-Control-Allow-Headers",
			        "origin, content-type, accept, authorization")
			      .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD")*/
				.build();
	}
}


