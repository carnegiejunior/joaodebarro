package com.joaodebarro.resident.core;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public class PageableTranslator {

    public static Pageable translate(Pageable pageable, Map<String,String> mappedFields){
        List<Sort.Order> orders = pageable.getSort().stream()
                .filter(order->mappedFields.containsKey(order.getProperty()))
                .map((order) -> new Sort.Order(order.getDirection(), mappedFields.get(order.getProperty())))
                .toList();
//        return PageRequest.of(pageable.getPageSize(),pageable.getPageSize(),Sort.by(orders));
        return PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),Sort.by(orders));
    }

}
