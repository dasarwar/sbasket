package com.dewan.sbasket.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.dewan.sbasket.domain.Product;
import com.dewan.sbasket.domain.SummaryPrice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductPriceCalculatorTest {
    private ProductPriceCalculator testCalculator;

    @Before
    public void setup() {
        testCalculator  = new ProductPriceCalculator(20);
    }

    @Test
    public void shouldCalculateSummaryPrice() {
        //given
        Product[] products  = {
                new Product("Product 1", new BigDecimal("1.75")),
                new Product("Product 2", new BigDecimal("1.50")),
                new Product("Product 3", new BigDecimal("1.75"))
        };
        List<Product> givenProducts = Arrays.asList(products);

        //when
        SummaryPrice summaryPrice = testCalculator.calculate(givenProducts);

        //then
        assertThat(summaryPrice, is(notNullValue()));
        assertThat(summaryPrice.getGross(), is(equalTo(new BigDecimal("5.0"))));
        assertThat(summaryPrice.getVat(), is(new BigDecimal("0.83")));
    }
}
