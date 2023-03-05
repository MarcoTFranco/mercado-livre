package com.MarketPlace.MercadoLivre.model.util.enums;

public enum StatusPagseguro {
    SUCESS, ERROR;

    public StatusTransaction verification() {
        if (this.equals(SUCESS)) {
            return StatusTransaction.sucess;
        }
        return StatusTransaction.error;
    }
}
