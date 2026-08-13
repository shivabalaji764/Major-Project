package com.project.restaurantpreorder.service;


import com.project.restaurantpreorder.dto.CustomerRegistrationDTO;
import com.project.restaurantpreorder.model.Accounts;
import com.project.restaurantpreorder.model.enums.Role;
import com.project.restaurantpreorder.repository.AccountsRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

    private final AccountsRepository accountsRepository;

    public AccountsService(AccountsRepository accountsRepository){
        this.accountsRepository = accountsRepository;
    }

    public void register(CustomerRegistrationDTO customer){
        Accounts account = customerRegistrationDTOTOAccounts(customer);
        accountsRepository.save(account);
    }

    public Accounts customerRegistrationDTOTOAccounts(CustomerRegistrationDTO customer){
         return Accounts.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobile(customer.getMobile())
                .role(Role.Customer)
                .password(customer.getPassword())
                .build();
    }
}
