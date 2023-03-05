package com.MarketPlace.MercadoLivre.model.util;

import com.MarketPlace.MercadoLivre.model.entities.Payment;
import com.MarketPlace.MercadoLivre.model.entities.Transaction;

public interface ReturnGatewayPayment {

    Transaction toTransaction(Payment payment);

}
