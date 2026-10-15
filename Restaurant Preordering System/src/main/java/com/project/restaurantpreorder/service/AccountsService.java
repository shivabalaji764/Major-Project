package com.project.restaurantpreorder.service;


import com.project.restaurantpreorder.dto.LoginDTO;
import com.project.restaurantpreorder.dto.LoginResponseDTO;
import com.project.restaurantpreorder.dto.RegistrationDTO;
import com.project.restaurantpreorder.model.Accounts;
import com.project.restaurantpreorder.model.CustomerProfile;
import com.project.restaurantpreorder.model.enums.Role;
import com.project.restaurantpreorder.repository.AccountsRepository;
import com.project.restaurantpreorder.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AccountsService(AccountsRepository accountsRepository, CustomerRepository customerRepository,
                           AuthenticationManager authenticationManager, JWTService jwtService) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public void register(RegistrationDTO customer){
        try {
            Accounts account = RegistrationDTOTOAccounts(customer);
            CustomerProfile customerProfile = RegistrationDTOTOACustomerProfile(customer, account);
            accountsRepository.save(account);
            customerRepository.save(customerProfile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Accounts RegistrationDTOTOAccounts(RegistrationDTO customer){
         return Accounts.builder()
                .email(customer.getEmail())
                .mobile(customer.getMobile())
                .role(Role.Customer)
                .password(customer.getPassword())
                .build();
    }

    public CustomerProfile RegistrationDTOTOACustomerProfile(RegistrationDTO customer, Accounts account){
        return CustomerProfile.builder()
                .account(account)
                .accountId(account.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }

    public LoginResponseDTO verify(LoginDTO login) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
        );

        if(authentication.isAuthenticated()){
            Accounts account = accountsRepository.findByEmail(login.getEmail())
                    .orElseThrow(()->new RuntimeException("Account Not Found"));
            String token = jwtService.generateToken(account.getId(), account.getEmail(), account.getRole().toString());
            return LoginResponseDTO.builder()
                    .bearer(token)
                    .id(account.getId())
                    .role(account.getRole().toString())
                    .build();
        }
        throw new RuntimeException("InValid Credentials");
    }
}
