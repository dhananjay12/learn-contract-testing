package com.mynotes.springcloud.contract.producer;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class SpringCloudContractProducerApplicationTests {

	@Autowired
	private EmployeeController employeeController;
	
	@MockBean
	EmployeeRepository employeeRepository;

	
	@Before
	public void setup() {
		// Arrange
		Employee employee = new Employee();
		employee.setId(1);
		employee.setFname("Jane");
		employee.setLname("Doe");
		employee.setSalary(123000.00);
		employee.setGender("M");
		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

		StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(employeeController);
		RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
	}
}
