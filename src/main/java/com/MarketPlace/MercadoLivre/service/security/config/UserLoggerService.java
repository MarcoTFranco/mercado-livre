package com.MarketPlace.MercadoLivre.service.security.config;

import com.MarketPlace.MercadoLivre.model.entities.User;
import com.MarketPlace.MercadoLivre.service.security.auth.UserLogged;
import com.MarketPlace.MercadoLivre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserLoggerService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = repository.findByEmail(username);
        return userInfo.map(UserLogged::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
