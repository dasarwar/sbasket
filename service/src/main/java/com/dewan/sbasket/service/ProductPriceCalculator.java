package com.dewan.sbasket.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import com.dewan.sbasket.domain.Product;
import com.dewan.sbasket.domain.SummaryPrice;

public class ProductPriceCalculator {
    private double vat = 20.00;

    public ProductPriceCalculator() {
    }

    public ProductPriceCalculator(double vat) {
        this.vat = vat;
    }

    public SummaryPrice calculate(List<Product> products) {
        long totalInPence;
        long totalVatInPence;
        totalInPence = Math.round(products.stream().mapToDouble(product -> product.getUnitPrice().doubleValue()*100.0).sum());
        totalVatInPence = Math.round(totalInPence * this.vat/120);
        double totalPound = totalInPence/100.0;
        double vatPound = totalVatInPence/100.0;
        return new SummaryPrice(new BigDecimal(totalPound + ""), new BigDecimal(vatPound +""));
    }
}
