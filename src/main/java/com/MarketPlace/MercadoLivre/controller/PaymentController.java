package com.MarketPlace.MercadoLivre.controller;

import com.MarketPlace.MercadoLivre.model.entities.Payment;
import com.MarketPlace.MercadoLivre.model.entities.Product;
import com.MarketPlace.MercadoLivre.model.entities.User;
import com.MarketPlace.MercadoLivre.model.request.PaymentRequest;
import com.MarketPlace.MercadoLivre.model.util.enums.GatewayPayment;
import com.MarketPlace.MercadoLivre.service.PaymentService;
import com.MarketPlace.MercadoLivre.service.exceptions.ProblemWithStockException;
import com.MarketPlace.MercadoLivre.service.security.auth.UserLogged;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/payments")
    public String createPayment(@RequestBody @Valid PaymentRequest request,
                                @AuthenticationPrincipal UserLogged userLogged,
                                UriComponentsBuilder uriComponentsBuilder) {

        Product product = service.findByIdProduct(request.getProductId());
        boolean slaughter = product.toBePurchasedSlaughterInStock(request.getAmount());
        if (slaughter) {
            User userPayment = service.findByEmailUser(userLogged.getEmail());
            GatewayPayment gateway = request.getGateway();

            Payment payment = new Payment(product, request.getAmount(), userPayment, gateway);
            service.createPayment(payment);

            if (gateway.equals(GatewayPayment.pagseguro)) {
                String urlReturnPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                        .buildAndExpand(payment.getId()).toString();
                return "pagseguro.com/" + payment.getId()
                        + "?redirectUrl=" + urlReturnPagseguro;
            } else {
                String urlReturnPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}")
                        .buildAndExpand(payment.getId()).toString();
                return "paypal.com/" + payment.getId()
                        + "?redirectUrl=" + urlReturnPaypal;
            }
        }
        throw new ProblemWithStockException("Nao foi possivel realizar a compra por conta do estoque");
    }

}
