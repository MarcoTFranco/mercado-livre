package com.MarketPlace.MercadoLivre.model.request;

import com.MarketPlace.MercadoLivre.model.entities.User;
import com.MarketPlace.MercadoLivre.service.annotations.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class UserRequest {
    @Email
    @NotBlank
    @UniqueValue(fieldName = "email", domainClass = User.class)
    private String email;
    @Length(min = 6)
    @NotBlank
    private String password;

    @Deprecated
    public UserRequest() {
    }

    public UserRequest(@Email @NotBlank String email, @Length(min = 6) @NotBlank String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toModel() {
        return new User(email, password);
    }
}
