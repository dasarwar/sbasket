package com.dewan.sbasket.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductTest {

    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void shouldPrintAllPropertyWhenPresent() throws IOException {
        //given
        Product product = new Product("A title", new BigDecimal("1.75"), "a description", 45);

        //when
        String jsonString = objectMapper.writeValueAsString(product);

        //then
        System.out.println(jsonString);
        assertThat(jsonString, is(equalTo("{\"title\":\"A title\",\"kcal_per_100g\":45.0,\"unit_price\":1.75,\"description\":\"a description\"}")));
    }

    @Test
    public void shouldNotPrintCaloriesPer100gramPropertyWhenNull() throws IOException {
        //given
        Product product = new Product("A title", new BigDecimal("1.75"), "a description");

        //when
        String jsonString = objectMapper.writeValueAsString(product);
        System.out.println(jsonString);

        //then
        assertThat(jsonString, is(equalTo("{\"title\":\"A title\",\"unit_price\":1.75,\"description\":\"a description\"}")));
    }

    @Test
    public void shouldFormatBigDecimalFromDecimalNumber() throws IOException {
        //given
        Product product = new Product("A title", new BigDecimal(.75), "a description");

        //when
        String jsonString = objectMapper.writeValueAsString(product);
        System.out.println(jsonString);

        //then
        assertThat(jsonString, is(equalTo("{\"title\":\"A title\",\"unit_price\":0.75,\"description\":\"a description\"}")));
    }

    @Test
    public void shouldFormatBigDecimalPriceFromIntegerNumber() throws IOException {
        //given
        Product product = new Product("A title", new BigDecimal(1), "a description");

        //when
        String jsonString = objectMapper.writeValueAsString(product);
        System.out.println(jsonString);

        //then
        assertThat(jsonString, is(equalTo("{\"title\":\"A title\",\"unit_price\":1.00,\"description\":\"a description\"}")));
    }

    @Test
    public void shouldOnlyPrintSingleLineDescription() throws IOException {
        //given
        String multiLineDescription = "A long description.\n" +
                "A long description.\n" +
                "A long description.\n" +
                "A long description.";
        Product product = new Product("A title", new BigDecimal(1), multiLineDescription);

        //when
        String jsonString = objectMapper.writeValueAsString(product);
        System.out.println(jsonString);

        //then
        assertThat(jsonString, is(equalTo("{\"title\":\"A title\",\"unit_price\":1.00,\"description\":\"A long description.\"}")));
    }
}
