package com.dewan.sbasket.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SummaryPriceTest {
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void shouldPrintSummaryPriceFields() throws IOException {
        SummaryPrice price = new SummaryPrice(new BigDecimal("8"), new BigDecimal("1.2"));

        String jsonString = objectMapper.writeValueAsString(price);

        System.out.println(jsonString);
        assertThat(jsonString, is(equalTo("{\"gross\":8.00,\"vat\":1.20}")));
    }

}
