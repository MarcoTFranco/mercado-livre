package com.MarketPlace.MercadoLivre.controller;

import com.MarketPlace.MercadoLivre.controller.exceptions.ProblemWithStockException;
import com.MarketPlace.MercadoLivre.model.entities.Payment;
import com.MarketPlace.MercadoLivre.model.entities.Product;
import com.MarketPlace.MercadoLivre.model.entities.User;
import com.MarketPlace.MercadoLivre.model.request.PagSeguroRequest;
import com.MarketPlace.MercadoLivre.model.request.PaymentRequest;
import com.MarketPlace.MercadoLivre.model.request.PaypalRequest;
import com.MarketPlace.MercadoLivre.model.util.ReturnGatewayPayment;
import com.MarketPlace.MercadoLivre.model.util.enums.GatewayPayment;
import com.MarketPlace.MercadoLivre.service.PaymentService;
import com.MarketPlace.MercadoLivre.service.UserService;
import com.MarketPlace.MercadoLivre.service.security.auth.UserLogged;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private UserService userService;


    @PostMapping("/payments")
    public String createPayment(@RequestBody @Valid PaymentRequest request,
                                @AuthenticationPrincipal UserLogged userLogged,
                                UriComponentsBuilder uriComponentsBuilder) {

        Product product = paymentService.find(Product.class, request.getProductId());
        boolean beatStockSuccessful = product.beatStock(request.getAmount());
        if (beatStockSuccessful) {
            User userPayment = userService.findByEmail(userLogged.getEmail());
            GatewayPayment gateway = request.getGateway();

            Payment payment = new Payment(product, request.getAmount(), userPayment, gateway);
            paymentService.insert(payment);

            return payment.generatePaymentGatewayUrl(uriComponentsBuilder);
        }
        throw new ProblemWithStockException("Nao foi possivel realizar a compra por conta do estoque");
    }

    @PostMapping("/retorno-pagseguro/{id}")
    public String processingPagSeguro(@PathVariable("id") Long paymentId,
                                      @RequestBody @Valid PagSeguroRequest request) {
        return processing(paymentId, request);
    }

    @PostMapping("/retorno-paypal/{id}")
    public String processingPayPal(@PathVariable("id") Long paymentId,
                                   @RequestBody @Valid PaypalRequest request) {
        return processing(paymentId, request);
    }

    public String processing (Long id, ReturnGatewayPayment returnGatewayPayment) {
        Payment payment = paymentService.find(Payment.class, id);
        payment.addsTransaction(returnGatewayPayment);
        paymentService.insert(payment);

        if (payment.successfullyProcessed()) {
            //falar com nf
            paymentService.processTaxNote(payment);
            //falar com ranking
            paymentService.processRanking(payment);
            //mandar email para quem comprou
            paymentService.processEmail(payment);
        }

        return payment.toString();
    }

}
