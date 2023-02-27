package com.MarketPlace.MercadoLivre.service.validator;

import com.MarketPlace.MercadoLivre.model.request.FeaturesRequest;
import com.MarketPlace.MercadoLivre.model.request.ProductRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

class ProhibitCharacteristicWithEqualNameValidatorTest {

    private static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of(false, List.of(
                        new FeaturesRequest("key", "value"))),
                Arguments.of(true, List.of(
                        new FeaturesRequest("key", "value"),
                        new FeaturesRequest("key", "value"))));
    }

    @ParameterizedTest
    @MethodSource("generator")
    @DisplayName("nao aceita produto com caracteristica igual")
    void test1(boolean expectedResult, List<FeaturesRequest> features) throws Exception {

        ProductRequest request = new ProductRequest("name", BigDecimal.TEN, 10,
                "description", 1L, features);

        ProhibitCharacteristicWithEqualNameValidator validator = new ProhibitCharacteristicWithEqualNameValidator();
        Errors errors = new BeanPropertyBindingResult(request, "test");

        validator.validate(request, errors);
        Assertions.assertEquals(expectedResult, errors.hasFieldErrors("features"));

    }

}