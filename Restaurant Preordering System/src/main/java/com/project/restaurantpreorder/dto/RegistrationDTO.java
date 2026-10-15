package com.project.restaurantpreorder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String password;
}
