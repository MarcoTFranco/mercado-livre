package com.MarketPlace.MercadoLivre.model.request;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

class ProductRequestTest {

    private static Stream<Arguments> gerador() {
        return Stream.of(
                Arguments.of(0, List.of()),
                Arguments.of(0, List.of(new FeaturesRequest("key", "value"))),
                Arguments.of(0, List.of(
                        new FeaturesRequest("key", "value"),
                        new FeaturesRequest("key2", "value2"))),
                Arguments.of(1, List.of(
                        new FeaturesRequest("key", "value"),
                        new FeaturesRequest("key", "value"))),
                Arguments.of(2, List.of(
                        new FeaturesRequest("key", "value"),
                        new FeaturesRequest("key", "value"),
                        new FeaturesRequest("key2", "value2"),
                        new FeaturesRequest("key2", "value2")))
        );
    }

    @DisplayName("cria produto com diversos tipos de caracteristicas")
    @ParameterizedTest
    @MethodSource("gerador")
    void test1(int expected, List<FeaturesRequest> features) throws Exception {
        ProductRequest request = new ProductRequest("name", BigDecimal.TEN, 10,
                "description", 1L, features);

        Assertions.assertEquals(expected, request.haveCharacteristicEqual().size());
    }


}