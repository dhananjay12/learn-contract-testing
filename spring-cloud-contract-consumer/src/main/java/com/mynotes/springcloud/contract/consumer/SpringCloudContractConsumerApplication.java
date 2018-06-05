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

	public String firstname;

	public String lname;

	public Person(final int id, final String firstname, final String lname) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lname = lname;
	}

	public Person() {
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(final String lname) {
		this.lname = lname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

}
