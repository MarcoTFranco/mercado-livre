package com.MarketPlace.MercadoLivre.model.request;

import com.MarketPlace.MercadoLivre.model.entities.NewUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public class NewUserRequest {
    @Email
    @NotBlank
    private String login;
    @Length(min = 6)
    @NotBlank
    private String password;

    public NewUserRequest(@Email @NotBlank String login, @Length(min = 6) @NotBlank String password) {
        this.login = login;
        this.password = password;
    }

    public NewUser toModel() {
        return new NewUser(login, password);
    }
}
