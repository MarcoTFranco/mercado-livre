package com.MarketPlace.MercadoLivre.model.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UserTest {

    @DisplayName("so aceita senha com 6 ou mais caracteres")
    @ParameterizedTest
    @CsvSource({
            "123456", "1234567", "1234564872121"
    })
    void test1(String password) throws Exception {
        User userRequest = new User("user@gmail.com", password);
    }

    @DisplayName("nao aceita senha com menos de 6 caracteres")
    @ParameterizedTest
    @CsvSource({
            "12345", "1234", "123"
    })
    void test2(String password) throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new User("user@gmail.com", password));
    }

}