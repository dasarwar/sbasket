package com.dewan.sbasket.domain.util;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public final class BigDecimalSerializer extends JsonSerializer<BigDecimal> {
    public BigDecimalSerializer() {}

    @Override
    public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        jgen.writeRawValue(value.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    }
}
