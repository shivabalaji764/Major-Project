package com.project.restaurantpreorder.controller;

import com.project.restaurantpreorder.dto.LoginDTO;
import com.project.restaurantpreorder.dto.LoginResponseDTO;
import com.project.restaurantpreorder.dto.RegistrationDTO;
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
    public ResponseEntity<?> register(@RequestBody RegistrationDTO account){
        try {
            account.setPassword(encoder.encode(account.getPassword()));
            accountsService.register(account);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Registration successful");
        } catch (Exception exception){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(exception.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO account){
        try{
            LoginResponseDTO response = accountsService.verify(account);
            return ResponseEntity.status(200).body(response);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
