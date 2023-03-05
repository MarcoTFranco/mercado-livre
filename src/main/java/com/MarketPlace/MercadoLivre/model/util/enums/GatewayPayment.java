package com.MarketPlace.MercadoLivre.model.util.enums;

import com.MarketPlace.MercadoLivre.model.entities.Payment;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPayment {
    pagseguro {
        @Override
        public String createUrlReturn(Payment payment,
                                      UriComponentsBuilder uriComponentsBuilder) {
            String urlReturnPagseguro = uriComponentsBuilder
                    .path("/retorno-pagseguro/{id}")
                    .buildAndExpand(payment.getId()).toString();
            return "pagseguro.com/" + payment.getId()
                    + "?redirectUrl=" + urlReturnPagseguro;
        }
    },
    paypal {
        @Override
        public String createUrlReturn(Payment payment,
                                      UriComponentsBuilder uriComponentsBuilder) {
            String urlReturnPaypal = uriComponentsBuilder
                    .path("/retorno-paypal/{id}")
                    .buildAndExpand(payment.getId()).toString();
            return "paypal.com/" + payment.getId()
                    + "?redirectUrl=" + urlReturnPaypal;
        }
    };

    public abstract String createUrlReturn(Payment payment,
                                           UriComponentsBuilder uriComponentsBuilder);
}
