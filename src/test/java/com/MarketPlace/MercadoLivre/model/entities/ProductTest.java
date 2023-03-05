package com.MarketPlace.MercadoLivre.model.entities;

import com.MarketPlace.MercadoLivre.model.request.FeaturesRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

class ProductTest {

    private static Stream<Arguments> testGenerator1() {
        return Stream.of(
                Arguments.of(
                        List.of(new FeaturesRequest("key", "value"),
                                new FeaturesRequest("key2", "value2"),
                                new FeaturesRequest("key3", "value3"))),
                Arguments.of(
                        List.of(
                                new FeaturesRequest("key", "value"),
                                new FeaturesRequest("key2", "value2"),
                                new FeaturesRequest("key3", "value3"),
                                new FeaturesRequest("key4", "value4"))));
    }

    private static Stream<Arguments> testGenerator2() {
        return Stream.of(
                Arguments.of(
                        List.of(new FeaturesRequest("key", "value"),
                                new FeaturesRequest("key2", "value2"))),
                Arguments.of(
                        List.of(
                                new FeaturesRequest("key", "value"))));
    }

    @DisplayName("um produto precisa de no mínimo 3 características")
    @ParameterizedTest
    @MethodSource("testGenerator1")
    void test1(Collection<FeaturesRequest> features) throws Exception {
        Category category = new Category("category");
        User owner = new User("email@gmail.com", "senhaa");

        new Product("name", BigDecimal.TEN, 10, "description",
                category, owner, features);
    }

    @DisplayName("um produto nao pode ser criado com menos de 3 caracteristicas")
    @ParameterizedTest
    @MethodSource("testGenerator2")
    void test2(Collection<FeaturesRequest> features) throws Exception {
        Category category = new Category("category");
        User owner = new User("email@gmail.com", "senhaa");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Product("name", BigDecimal.TEN, 10, "description",
                    category, owner, features);
        });
    }

    @DisplayName("verifica o estoque do produto")
    @ParameterizedTest
    @CsvSource({ "1,1,true", "1,2,false", "4,2,true", "1,5,false"})
    void test3(int stock, int amountRequest, boolean resultExpected) throws Exception {
        List<FeaturesRequest> features = List.of(
                new FeaturesRequest("key1", "value1"),
                new FeaturesRequest("key2", "value2"),
                new FeaturesRequest("key3", "value3"));
        Category category = new Category("category");
        User user = new User("user@gmail.com", "123456");
        Product product = new Product("name", BigDecimal.TEN,
                stock, "description", category, user, features);

        boolean result = product.beatStock(amountRequest);

        Assertions.assertEquals(resultExpected, result);
    }

    @DisplayName("não aceita abater estoque <= zero")
    @ParameterizedTest
    @CsvSource({"0", "-1", "-4", "-122"})
    void test4(int stock) throws Exception {
        List<FeaturesRequest> features = List.of(
                new FeaturesRequest("key1", "value1"),
                new FeaturesRequest("key2", "value2"),
                new FeaturesRequest("key3", "value3"));
        Category category = new Category("category");
        User user = new User("user@gmail.com", "123456");
        Product product = new Product("name", BigDecimal.TEN,
                stock, "description", category, user, features);

        Assertions.assertThrows(IllegalArgumentException.class, () -> product.beatStock(stock));
    }

}