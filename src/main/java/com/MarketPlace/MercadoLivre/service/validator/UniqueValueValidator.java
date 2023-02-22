package com.MarketPlace.MercadoLivre.service.validator;

import com.MarketPlace.MercadoLivre.service.annotations.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    @Autowired
    private EntityManager manager;

    private String field;
    private Class<?> className;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        field = constraintAnnotation.fieldName();
        className = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        Query query =
                manager.createQuery("select " + field + " from " + className.getName() + " where " + field + " =:value")
                        .setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <= 1,
                "Foi encontrado " + className + " com o " + field + " repetido");
        return list.isEmpty();
    }
}
