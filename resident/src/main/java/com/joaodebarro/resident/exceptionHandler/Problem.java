package com.joaodebarro.resident.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class Problem {

    private Integer status;

    private String type;

    private String title;

    private String detail;

    private String userMessage;

    private OffsetDateTime timestamp;

    private List<Object> objects;

    @Builder
    @Getter
    public static class Object {
        private String name;
        private String userMessage;
    }

}
