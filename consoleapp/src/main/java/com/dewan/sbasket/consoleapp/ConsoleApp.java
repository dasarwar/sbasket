package com.dewan.sbasket.consoleapp;

import com.dewan.sbasket.consoleapp.view.ConsoleView;
import com.dewan.sbasket.domain.Product;
import com.dewan.sbasket.domain.SummaryPrice;
import com.dewan.sbasket.service.ProductPriceCalculator;
import com.dewan.sbasket.service.api.ProductParserService;
import com.dewan.sbasket.service.impl.JsoupProductParser;

import java.util.List;

public class ConsoleApp {
    static String PRODUCTS_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
    static double VAT = 20.0;

    public static void main(String[] args) {
         try {
                ProductParserService productHtmlParser = new JsoupProductParser();
                ProductPriceCalculator priceCalculator = new ProductPriceCalculator(VAT);
                List<Product> productList = productHtmlParser.parseProducts(PRODUCTS_URL);
                SummaryPrice summaryPrice = priceCalculator.calculate(productList);
                ConsoleView consoleView = new ConsoleView(productList, summaryPrice);
                System.out.println(consoleView.productsJson());
         } catch (Exception ex) {
                ex.printStackTrace();
         }

    }
}
