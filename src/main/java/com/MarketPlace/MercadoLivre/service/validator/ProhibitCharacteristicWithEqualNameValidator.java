package com.MarketPlace.MercadoLivre.service.validator;

import com.MarketPlace.MercadoLivre.model.request.ProductRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProhibitCharacteristicWithEqualNameValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        ProductRequest request = (ProductRequest) target;
        Set<String> equalName = request.haveCharacteristicEqual();
        if (!equalName.isEmpty()) {
            errors.reject("features", null, "Você tem caracteristicas iguais" + equalName);
        }

    }
}
