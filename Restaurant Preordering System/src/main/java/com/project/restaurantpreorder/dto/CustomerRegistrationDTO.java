package com.project.restaurantpreorder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationDTO {
    private String name;
    private String mobile;
    private String email;
    private String password;
}
