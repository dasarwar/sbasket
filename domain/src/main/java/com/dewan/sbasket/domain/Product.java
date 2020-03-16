package com.dewan.sbasket.domain;

import java.math.BigDecimal;

import com.dewan.sbasket.domain.util.BigDecimalSerializer;
import com.dewan.sbasket.domain.util.SingleLineSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonPropertyOrder({
        "title",
        "caloriesPer100gram",
        "unitPrice",
        "description"
})
public class Product {
    private String title;

    @JsonProperty("description")
    @JsonSerialize(using = SingleLineSerializer.class)
    private String description;

    @JsonProperty("unit_price")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal unitPrice;

    @JsonProperty("kcal_per_100g")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int caloriesPer100gram;

    public Product() {}

    public Product(String title, BigDecimal unitPrice) {
        this.title = title;
        this.unitPrice = unitPrice;
    }

    public Product(String title, BigDecimal unitPrice, String description) {
        this(title, unitPrice);
        this.description = description;
    }

    public Product(String title, BigDecimal unitPrice, String description, int caloriesPer100gram) {
        this(title, unitPrice, description);
        this.caloriesPer100gram = caloriesPer100gram;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getCaloriesPer100gram() {
        return caloriesPer100gram;
    }

    public void setCaloriesPer100gram(int caloriesPer100gram) {
        this.caloriesPer100gram = caloriesPer100gram;
    }

    @Override
    public String toString() {
        return "Product[title: " + title + ", unitPrie: " + unitPrice +
                ", description: " + (description != null ? description : "") +
                ", caloriesPer100gram: " + caloriesPer100gram + "]";
    }

}

