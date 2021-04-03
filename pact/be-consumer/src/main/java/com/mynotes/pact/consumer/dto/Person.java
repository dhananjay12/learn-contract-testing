package com.mynotes.pact.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Person {

    @NotBlank(message = "Name can not be null!")
    private String name;

    @NotBlank(message = "Email can not be null!")
    @Email(message = "Invalid Email")
    private String email;
}
