package com.mynotes.springcloud.contract.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringCloudContractConsumerApplication {

	public static void main(final String[] args) {
		SpringApplication.run(SpringCloudContractConsumerApplication.class, args);
	}
}

@Component
class ConsumerClient {

	public Person getPerson(final int id) {

		final RestTemplate restTemplate = new RestTemplate();

		final ResponseEntity<Person> result = restTemplate.exchange("http://localhost:8081/employee/" + id,
				HttpMethod.GET, null, Person.class);

		return result.getBody();
	}
}

class Person {

	private int id;

	public String fname;

	public String lname;

	public Person(final int id, final String fname, final String lname) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
	}

	public Person() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

}
