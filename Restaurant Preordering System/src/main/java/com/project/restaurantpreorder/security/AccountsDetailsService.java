package com.project.restaurantpreorder.security;

import com.project.restaurantpreorder.model.Accounts;
import com.project.restaurantpreorder.repository.AccountsRepository;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountsDetailsService implements UserDetailsService {

    private final AccountsRepository accountsRepository;

    public AccountsDetailsService(AccountsRepository accountsRepository){
        this.accountsRepository = accountsRepository;
    }

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Accounts account = accountsRepository
                .findByEmail(email)
                .orElseThrow(()->
                    new UsernameNotFoundException(
                            "Customer not found"
                    )
                );
        return new AccountsPrincipal(account);
    }
}
