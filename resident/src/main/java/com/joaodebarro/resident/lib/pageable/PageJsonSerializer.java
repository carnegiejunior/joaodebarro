package com.joaodebarro.resident.lib.pageable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {
    @Override
    public void serialize(Page<?> page, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();

        gen.writeObjectField("content",page.getContent());

        gen.writeObjectFieldStart("pagination");
        gen.writeNumberField("size",page.getSize());
        gen.writeNumberField("totalElements",page.getTotalElements());
        gen.writeNumberField("totalPages",page.getTotalPages());
        gen.writeNumberField("page",page.getNumber());
        gen.writeBooleanField("hasNext",page.hasNext());
        gen.writeBooleanField("hasPrevious",page.hasPrevious());

        gen.writeEndObject();

    }
}


//@JsonComponent
//public class PageJsonSerializer extends JsonSerializer<Page<?>> {
//    @Override
//    public void serialize(Page<?> page, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//        gen.writeStartObject();
//        gen.writeObjectField("data", page.getContent().stream()
//                .map(entity -> {
//                    Map<String, Object> map = new LinkedHashMap<>();
//                    map.put("id", entity.getId());
//                    map.put("nome", entity.getNome());
//                    map.put("data", entity.getData());
//                    return map;
//                }).collect(Collectors.toList()));
//
//        gen.writeObjectFieldStart("pagination");
//        gen.writeNumberField("page", page.getNumber() + 1);
//        gen.writeNumberField("limit", page.getSize());
//        // add more pagination fields as needed
//        gen.writeEndObject();
//
//        gen.writeObjectField("_links", Collections.emptyMap());
//        gen.writeEndObject();
//    }
//}

