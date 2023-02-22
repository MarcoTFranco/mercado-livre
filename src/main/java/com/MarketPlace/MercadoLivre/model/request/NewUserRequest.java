package com.MarketPlace.MercadoLivre.model.request;

import com.MarketPlace.MercadoLivre.model.entities.NewUser;
import com.MarketPlace.MercadoLivre.service.annotations.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

public class NewUserRequest {
    @Email
    @NotBlank
    @UniqueValue (fieldName = "login", domainClass = NewUser.class)
    private String login;
    @Length(min = 6)
    @NotBlank
    private String password;

    public NewUserRequest(@Email @NotBlank String login, @Length(min = 6) @NotBlank String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public NewUser toModel() {
        return new NewUser(login, password);
    }
}
