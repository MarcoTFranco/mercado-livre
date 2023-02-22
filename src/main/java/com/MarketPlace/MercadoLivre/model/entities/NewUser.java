package com.MarketPlace.MercadoLivre.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.xml.crypto.Data;
import java.time.LocalDate;

@Entity
@Table(name = "tb_user")
public class NewUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @NotBlank
    private String login;
    @Length(min = 6)
    @NotBlank
    private String password;
    @NotNull
    private LocalDate instantOfCadastre;

    public NewUser(@Email @NotBlank String login,
                   @Length(min = 6) @NotBlank String password) {
        Assert.hasLength(login, "Não pode estar em branco a password");
        Assert.hasLength(password, "Não pode estar em branco a password");
        Assert.isTrue(password.length()>=6, "Senha tem que ter no mínimo 6 caracteres");

        this.login = login;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.instantOfCadastre = LocalDate.now();
    }

    @Override
    public String toString() {
        return "NewUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", instantOfCadastre=" + instantOfCadastre +
                '}';
    }
}
