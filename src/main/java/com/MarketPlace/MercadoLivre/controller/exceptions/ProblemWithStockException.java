package com.MarketPlace.MercadoLivre.controller.exceptions;

public class ProblemWithStockException extends RuntimeException{

    public ProblemWithStockException(String msg) {
        super(msg);
    }
}
