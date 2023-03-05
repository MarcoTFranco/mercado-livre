package com.MarketPlace.MercadoLivre.model.request;

import com.MarketPlace.MercadoLivre.model.entities.Payment;
import com.MarketPlace.MercadoLivre.model.entities.Transaction;
import com.MarketPlace.MercadoLivre.model.util.ReturnGatewayPayment;
import com.MarketPlace.MercadoLivre.model.util.enums.StatusTransaction;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class PaypalRequest implements ReturnGatewayPayment {
    @NotBlank
    private String transactionId;
    @Min(0)
    @Max(1)
    private int status;

    @Deprecated
    public PaypalRequest() {
    }

    public PaypalRequest(String transactionId, int status) {
        this.transactionId = transactionId;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public int getStatus() {
        return status;
    }

    public Transaction toTransaction(Payment payment) {
        StatusTransaction statusTransaction = this.status == 0? StatusTransaction.error:StatusTransaction.sucess;
        return new Transaction( statusTransaction, transactionId, payment);
    }
}
