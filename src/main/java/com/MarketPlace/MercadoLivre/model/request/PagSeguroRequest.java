package com.MarketPlace.MercadoLivre.model.request;

import com.MarketPlace.MercadoLivre.model.entities.Payment;
import com.MarketPlace.MercadoLivre.model.entities.Transaction;
import com.MarketPlace.MercadoLivre.model.util.ReturnGatewayPayment;
import com.MarketPlace.MercadoLivre.model.util.enums.StatusPagseguro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PagSeguroRequest implements ReturnGatewayPayment {
    @NotBlank
    private String transactionId;
    @NotNull
    private StatusPagseguro status;

    @Deprecated
    public PagSeguroRequest() {
    }

    public PagSeguroRequest(String transactionId, StatusPagseguro status) {
        this.transactionId = transactionId;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public StatusPagseguro getStatus() {
        return status;
    }

    public Transaction toTransaction(Payment payment) {
        return new Transaction(status.verification(), transactionId, payment);
    }

    @Override
    public String toString() {
        return "PagSeguroRequest{" +
                "transactionId='" + transactionId + '\'' +
                ", status=" + status +
                '}';
    }
}
