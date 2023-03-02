package com.MarketPlace.MercadoLivre.service.exceptions;

public class ProblemWithStockException extends RuntimeException{

    public ProblemWithStockException(String msg) {
        super(msg);
    }
}
