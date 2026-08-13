package com.project.restaurantpreorder.controller;

import com.project.restaurantpreorder.dto.CustomerRegistrationDTO;
import com.project.restaurantpreorder.service.AccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class AccountsController {

    private final AccountsService accountsService;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public AccountsController(AccountsService accountsService){
        this.accountsService = accountsService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CustomerRegistrationDTO customer){
        try {
            customer.setPassword(encoder.encode(customer.getPassword()));
            accountsService.register(customer);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Registration successful");
        } catch (Exception exception){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(exception.getMessage());
        }
    }
}
