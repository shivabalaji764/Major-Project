package com.project.restaurantpreorder.security;

import com.project.restaurantpreorder.model.Customer;
import com.project.restaurantpreorder.repository.CustomerRepository;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerDetailsService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository
                .findByEmail(email)
                .orElseThrow(()->
                    new UsernameNotFoundException(
                            "Customer not found"
                    )
                );
        return new CustomerPrincipal(customer);
    }
}
