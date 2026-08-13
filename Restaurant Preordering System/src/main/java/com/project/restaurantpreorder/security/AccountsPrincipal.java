package com.project.restaurantpreorder.security;

import com.project.restaurantpreorder.model.Accounts;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AccountsPrincipal implements UserDetails {

    private final Accounts account;

    public AccountsPrincipal(Accounts account){
        this.account = account;
    }

    @Override
    @NullMarked
    public String getUsername(){
        return account.getEmail();
    }

    @Override
    public String getPassword(){
        return account.getPassword();
    }

    @Override
    @NullMarked
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(
                new SimpleGrantedAuthority(
                        "Role "+account.getRole().name()
                )
        );
    }

    @Override
    public boolean isEnabled(){
        return account.isEnabled();
    }
}
