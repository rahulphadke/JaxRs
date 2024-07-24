This repository is created by Rahul Phadke to study JAX-RS
It has 2 projects :
1) jaxrsWebapp01 : is a Dynamic Webapp created in Eclipse. It runs on Tomcat server. Important programs are :
  * EmployeeService.java     - JAX-RS resource class, supports access from same origin
  * CorsEmployeeService.java - JAX-RS resource class, supports access from cross origin 
  * FrontEndJaxRs01.html -  Same origin frontend for CRUD operations on Employee object
  * jaxrsCrud.js - uses fetch Api to call JAX-RS resource
    
2) jsxrs01FrontEnd : is a static Webapp created in Eclipse. It run on HTTP preview server supported in eclipse. Important programs are :
  * FrontEndJaxRs02CrossOrigin.html - Cross origin frontend for CRUD operations on Employee object
  * jaxrsCrud.js - uses fetch Api to call JAX-RS resource
