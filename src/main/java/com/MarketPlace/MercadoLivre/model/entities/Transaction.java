package com.MarketPlace.MercadoLivre.model.entities;

import com.MarketPlace.MercadoLivre.model.util.enums.StatusTransaction;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;
@Entity
@Table(name = "tb_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private StatusTransaction status;
    @NotBlank
    private String transactionId;
    @NotNull
    @Valid
    @ManyToOne
    private Payment payment;
    @NotNull
    private LocalDateTime instante;

    @Deprecated
    public Transaction() {
    }

    public Transaction(StatusTransaction status, @NotBlank String transactionId, @NotNull @Valid Payment payment) {
        this.status = status;
        this.transactionId = transactionId;
        this.payment = payment;
        this.instante = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public StatusTransaction getStatus() {
        return status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Payment getPayment() {
        return payment;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public boolean successfullyCompleted () {
        return this.status.equals(StatusTransaction.sucess);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionId.equals(that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", status=" + status +
                ", transactionId='" + transactionId + '\'' +
                ", instante=" + instante +
                '}';
    }
}
