package com.MarketPlace.MercadoLivre.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @NotBlank
    private String email;
    @Length(min = 6)
    @NotBlank
    private String password;
    @NotNull
    private LocalDate instantOfCadastre;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.MERGE)
    private List<Product> products = new ArrayList<>();
    @OneToMany(mappedBy = "owner", cascade = CascadeType.MERGE)
    private List<ProductOpinion> opinions = new ArrayList<>();

    @Deprecated
    public User() {
    }

    public User(@Email @NotBlank String email, @Length(min = 6) @NotBlank String password) {
        Assert.hasLength(email, "Não pode estar em branco o email");
        Assert.hasLength(password, "Não pode estar em branco a password");
        Assert.isTrue(password.length() >= 6, "Senha tem que ter no mínimo 6 caracteres");

        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.instantOfCadastre = LocalDate.now();
    }

    public Long getId() {
        return id;
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

    public LocalDate getInstantOfCadastre() {
        return instantOfCadastre;
    }

    public void setInstantOfCadastre(LocalDate instantOfCadastre) {
        this.instantOfCadastre = instantOfCadastre;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<ProductOpinion> getOpinions() {
        return opinions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", instantOfCadastre=" + instantOfCadastre +
                '}';
    }
}
