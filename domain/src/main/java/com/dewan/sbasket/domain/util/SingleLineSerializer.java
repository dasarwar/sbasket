package com.dewan.sbasket.domain.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public final class SingleLineSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(value != null && value.indexOf("\n") > 0) {
            value = value.substring(0, value.indexOf("\n"));
        }
        gen.writeString(value);
    }
}
