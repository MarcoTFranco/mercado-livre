package com.MarketPlace.MercadoLivre.service.annotations;

import com.MarketPlace.MercadoLivre.service.validator.UniqueValueValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {UniqueValueValidator.class})
public @interface UniqueValue {
    String message() default "Campo deve ser único";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

    String fieldName();

    Class<?> domainClass();

}
