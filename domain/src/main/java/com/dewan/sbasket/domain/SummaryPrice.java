package com.dewan.sbasket.domain;

import java.math.BigDecimal;

import com.dewan.sbasket.domain.util.BigDecimalSerializer;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonPropertyOrder({
        "gross",
        "vat"
})
public class SummaryPrice {
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal gross;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal vat;

    public SummaryPrice(BigDecimal gross, BigDecimal vat) {
        this.gross = gross;
        this.vat = vat;
    }

    public BigDecimal getGross() {
        return gross;
    }

    public void setGross(BigDecimal gross) {
        this.gross = gross;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }
}
