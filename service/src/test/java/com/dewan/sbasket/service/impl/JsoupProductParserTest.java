package com.dewan.sbasket.service.impl;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import com.dewan.sbasket.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JsoupProductParserTest {

    JsoupProductParser testParser = new JsoupProductParser();

    static final String PRODUCTS_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
    static final String PRODUCT_DETAILS_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-200g.html";


    @Before
    public void setup() {
    }

    @Test
    public void shouldParseProducts() {
        List<Product> products = testParser.parseProducts(PRODUCTS_URL);
        assertThat(products, notNullValue());
        assertThat(products.size(), is(equalTo(17)));
    }

    @Test
    public void shouldPopulateExtraProductInfo() {
        JsoupProductParser.ProdExtraInfo prodExtraInfo = testParser.getExtraInfo(PRODUCT_DETAILS_URL);
        assertThat(prodExtraInfo, is(notNullValue()));
        assertThat(prodExtraInfo.getDescription(), is(equalTo("by Sainsbury's blueberries")));
        assertThat(prodExtraInfo.getKcal(), is(equalTo(45)));
    }
}

