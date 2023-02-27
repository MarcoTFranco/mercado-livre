package com.MarketPlace.MercadoLivre.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Não existe esse produto no usuario")
public class ProductNotBelongUserException extends RuntimeException{
    public ProductNotBelongUserException(String msg) {
        super(msg);
    }
}
