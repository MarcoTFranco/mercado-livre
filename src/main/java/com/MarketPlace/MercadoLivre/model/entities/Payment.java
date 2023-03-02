package com.MarketPlace.MercadoLivre.model.entities;

import com.MarketPlace.MercadoLivre.model.util.enums.GatewayPayment;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
@Entity
@Table(name = "tb_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    @Valid
    private Product product;
    @Positive
    private Integer amount;
    @ManyToOne
    @NotNull
    @Valid
    private User userPayment;
    @Enumerated
    @NotNull
    private GatewayPayment gatewayPayment;

    public Payment(@NotNull @Valid Product product,
                   @Positive Integer amount,
                   @NotNull @Valid User userPayment,
                   @NotNull GatewayPayment gatewayPayment) {
        this.product = product;
        this.amount = amount;
        this.userPayment = userPayment;
        this.gatewayPayment = gatewayPayment;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", product=" + product +
                ", amount=" + amount +
                ", userPayment=" + userPayment +
                ", gatewayPayment=" + gatewayPayment +
                '}';
    }
}
