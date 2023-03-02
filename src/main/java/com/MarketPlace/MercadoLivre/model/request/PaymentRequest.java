package com.MarketPlace.MercadoLivre.model.request;

import com.MarketPlace.MercadoLivre.model.util.enums.GatewayPayment;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PaymentRequest {
    @Min(1)
    private Integer amount;
    @NotNull
    private Long productId;
    @NotNull
    private GatewayPayment gateway;

    @Deprecated
    public PaymentRequest() {
    }

    public PaymentRequest(Integer amount, Long productId, GatewayPayment gateway) {
        this.amount = amount;
        this.productId = productId;
        this.gateway = gateway;
    }

    public Integer getAmount() {
        return amount;
    }

    public Long getProductId() {
        return productId;
    }

    public GatewayPayment getGateway() {
        return gateway;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "amount=" + amount +
                ", productId=" + productId +
                '}';
    }
}
