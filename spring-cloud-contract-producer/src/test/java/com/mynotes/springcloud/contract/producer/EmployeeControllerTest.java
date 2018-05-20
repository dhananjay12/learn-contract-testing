package com.mynotes.springcloud.contract.producer;

import javax.annotation.PostConstruct;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import java.util.Optional;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

	@LocalServerPort
	private int port;

	private String uri;
	
	
	@Autowired
	EmployeeRepository employeeRepository;


	@PostConstruct
	public void init() {
		uri = "http://localhost:" + port;
	}
	
	@After
	public void cleanRepo() {
		employeeRepository.deleteAll();
	}


	@Test
	public void getEmployeeFoundTest() {
		//Arrange
		Employee employee=new Employee();
		employee.setFname("Jane");
		employee.setLname("Doe");
		employee.setSalary(123000.00);
		employee.setGender("M");
		employeeRepository.save(employee);
		//Act-Assert
		get(uri + "/employee/1").then().assertThat().statusCode(200).body("fname", equalTo("Jane"));
	}

	@Test
	public void getEmployeeNotFoundTest() {
		get(uri + "/employee/21").then().assertThat().statusCode(404);
	}
	
	
}
