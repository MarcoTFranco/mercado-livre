package com.MarketPlace.MercadoLivre.model.util;

import jakarta.validation.constraints.NotBlank;

public interface Mailer {

    /**
     * @param body email body
     * @param subject email subject
     * @param nameFrom name to appear in the email provider
     * @param from source email
     * @param to destination email
     */

    void send(@NotBlank String body,
              @NotBlank String subject,
              @NotBlank String nameFrom,
              @NotBlank String from,
              @NotBlank String to);

}
