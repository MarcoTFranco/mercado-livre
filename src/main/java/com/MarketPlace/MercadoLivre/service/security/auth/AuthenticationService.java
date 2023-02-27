package com.MarketPlace.MercadoLivre.service.security.auth;

import com.MarketPlace.MercadoLivre.model.enums.Role;
import com.MarketPlace.MercadoLivre.repository.UserRepository;
import com.MarketPlace.MercadoLivre.service.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String authenticate(AuthenticationRequest request) {

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        UserLogged userLogged = new UserLogged(user);
        userLogged.setRole(Role.USER);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            var jwtToken = jwtService.generateToken(userLogged);
            return jwtToken;
        }
        return null;
    }
}
