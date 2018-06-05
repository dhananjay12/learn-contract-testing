package com.mynotes.springcloud.contract.producer;


import java.util.Optional;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest(classes = SpringCloudContractProducerApplication.class)
@RunWith(SpringRunner.class)
public class BaseClass {

    @Autowired
    EmployeeController         aEmployeeController;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Before
    public void before() {
        final Employee aEmployee = new Employee(1, "Jane", "Doe", 123000.00, "M");
        Mockito.when(this.employeeRepository.findById(1)).thenReturn(Optional.of(aEmployee));
        RestAssuredMockMvc.standaloneSetup(this.aEmployeeController);
    }

}
