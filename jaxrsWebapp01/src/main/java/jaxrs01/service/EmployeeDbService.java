package jaxrs01.service;

import java.util.List;

import jaxrs01.dao.EmployeeDao;
import jaxrs01.model.Employee;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/employeeDbService")
public class EmployeeDbService {

	private EmployeeDao dao;
	
	public EmployeeDbService() {
		dao = new EmployeeDao();
		System.out.println("EmployeeDbService()");
		System.out.println("***** dao=" + dao);

	}

	@OPTIONS
	@Path("/delete/{id}") 
	public Response crossOrigin() {
		System.out.println("EmployeeDbService::crossOrigin()");
		return Response.ok().header("Access-Control-Allow-Origin", "*")
//				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				.build();
	}

	@DELETE
	@Path("/delete/{id}") // endpoint URL = http://localhost:8080/employeeDbService/delete/100
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteEmployee(@PathParam("id") int id) {

		String res = dao.deleteEmpById(id);

		System.out.println(res);
		return Response.ok(res).header("Access-Control-Allow-Origin", "*")
//				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
	}

	@POST
	@Path("/save") // endpoint URL = http://localhost:8080/employeeDbService/save
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveEmployee(Employee e) {

		String res = dao.save(e);

		System.out.println("saveEmployee()");
		System.out.println("Emp added : " + e);
		return Response.ok(res).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
	}

	@GET
	@Path("/get/{id}")
	// endpoint URL = http://localhost:8080/employeeDbService/get/3
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployee(@PathParam("id") int id) {
		System.out.println("getEmployee() id=" + id);
		Employee temp = dao.getEmpById(id);

		return Response.ok(temp).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
	}

	@GET // endpoint URL = http://localhost:8080/employeeService
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEmployees() {
		System.out.println("** getAllEmployees()");
		List<Employee> empList = dao.getAllEmps();
		return Response.ok(empList).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
	}

}
