package com.mynotes.pact.consumer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mynotes.pact.consumer.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Value("${employee.uri}")
    private String uri;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Person person) {

        RestTemplate restTemplate = new RestTemplate();

        // create request body
        String input = "{\"name\":\"" + person.getName() + "\",\"email\":\"" + person.getEmail() + "\"}";
        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(input, headers);

        ResponseEntity<String> response = restTemplate
                .exchange(uri + "/employee", HttpMethod.POST, entity, String.class);

        return ResponseEntity.status(response.getStatusCode()).build();

    }

}
