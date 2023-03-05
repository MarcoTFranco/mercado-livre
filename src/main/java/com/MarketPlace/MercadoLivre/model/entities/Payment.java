package com.MarketPlace.MercadoLivre.model.entities;

import com.MarketPlace.MercadoLivre.model.util.ReturnGatewayPayment;
import com.MarketPlace.MercadoLivre.model.util.enums.GatewayPayment;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "payment", cascade = CascadeType.MERGE)
    private Set<Transaction> transaction = new HashSet<>();

    @Deprecated
    public Payment() {
    }

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

    public Product getProduct() {
        return product;
    }

    public Integer getAmount() {
        return amount;
    }

    public User getUserPayment() {
        return userPayment;
    }

    public GatewayPayment getGatewayPayment() {
        return gatewayPayment;
    }

    public Set<Transaction> getTransaction() {
        return transaction;
    }

    public String generatePaymentGatewayUrl(UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayPayment.createUrlReturn(this, uriComponentsBuilder);
    }

    public void addsTransaction(@Valid ReturnGatewayPayment request) {
        Transaction newTransaction = request.toTransaction(this);
        Assert.isTrue(!this.transaction.contains(newTransaction),
                "Já existe uma transacao igual a essa processada " + newTransaction);

        Assert.isTrue(transactionsSucessfullyCompleted().isEmpty(), "Essa compra ja foi concluida com sucesso");

        this.transaction.add(request.toTransaction(this));
    }

    public Set<Transaction> transactionsSucessfullyCompleted() {
        Set<Transaction> transactionsSucessfullyCompleted = this.transaction.stream()
                .filter(Transaction::successfullyCompleted)
                .collect(Collectors.toSet());

        Assert.isTrue(transactionsSucessfullyCompleted.size() <= 1,
                "Não pode ter mais de uma transação concluida com sucesso na compra");
        return transactionsSucessfullyCompleted;
    }

    public boolean successfullyProcessed() {
        return !transactionsSucessfullyCompleted().isEmpty();
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", product=" + product +
                ", amount=" + amount +
                ", userPayment=" + userPayment +
                ", gatewayPayment=" + gatewayPayment +
                ", transaction=" + transaction +
                '}';
    }
}
