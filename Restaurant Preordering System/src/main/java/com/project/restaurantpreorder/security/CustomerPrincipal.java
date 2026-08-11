package com.project.restaurantpreorder.security;

import com.project.restaurantpreorder.model.Customer;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomerPrincipal implements UserDetails {

    private final Customer customer;

    public CustomerPrincipal(Customer customer){
        this.customer = customer;
    }

    @Override
    @NullMarked
    public String getUsername(){
        return customer.getEmail();
    }

    @Override
    public String getPassword(){
        return customer.getPassword();
    }

    @Override
    @NullMarked
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(
                new SimpleGrantedAuthority(
                        "Role "+customer.getRole().name()
                )
        );
    }

    @Override
    public boolean isEnabled(){
        return customer.isEnabled();
    }
}
