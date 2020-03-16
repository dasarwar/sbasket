package com.dewan.sbasket.consoleapp.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dewan.sbasket.domain.Product;
import com.dewan.sbasket.domain.SummaryPrice;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConsoleView {
    @JsonProperty("results")
    private List<Product> products;

    @JsonProperty("total")
    private SummaryPrice summaryPrice;

    public ConsoleView(List<Product> products, SummaryPrice price) {
        this.products = new ArrayList<>(products);
        this.summaryPrice = price;
    }

    /**
     * Returns JSON String representing the products
     * @return JSON String
     * @throws IOException
     */
    public String productsJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

}
