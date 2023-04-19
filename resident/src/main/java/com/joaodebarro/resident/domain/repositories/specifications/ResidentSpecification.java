package com.joaodebarro.resident.domain.repositories.specifications;

import com.joaodebarro.resident.domain.entities.ResidentEntity;
import jakarta.persistence.criteria.Expression;
import org.springframework.data.jpa.domain.Specification;

public class ResidentSpecification {

    public static Specification<ResidentEntity> withCPF(String cpf){
        return (root,query,criteriaBuilder)->
                criteriaBuilder.equal(criteriaBuilder.lower(root.get("cpf"))
                        , criteriaBuilder.lower(criteriaBuilder.literal(cpf)));
    }

    public static Specification<ResidentEntity> withPartialName(String name){
        return (root,query,criteriaBuilder)->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name"))
                        , criteriaBuilder.lower(criteriaBuilder.literal("%"+ name +"%")));
    }
}
