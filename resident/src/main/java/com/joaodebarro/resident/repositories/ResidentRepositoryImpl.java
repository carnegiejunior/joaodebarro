package com.joaodebarro.resident.repositories;

import com.joaodebarro.resident.entities.ResidentEntity;
import com.joaodebarro.resident.dtos.ResidentRequestQueryDTO;
import com.joaodebarro.resident.lib.PredicateBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class ResidentRepositoryImpl implements ResidentRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<ResidentEntity>> findAllResidents(ResidentRequestQueryDTO request, Pageable pageable) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ResidentEntity> criteriaBuilderQuery = criteriaBuilder.createQuery(ResidentEntity.class);
            Root<ResidentEntity> from = criteriaBuilderQuery.from(ResidentEntity.class);
//            from.fetch("residentialUnit", JoinType.LEFT);
            criteriaBuilderQuery.select(from);
            criteriaBuilderQuery.where(getPredicate(request, criteriaBuilder, from));
            List<ResidentEntity> result = entityManager.createQuery(criteriaBuilderQuery).getResultList();
            return Optional.of(result);
        } finally {
            entityManager.close();
        }
    }

    private static Predicate getPredicate(ResidentRequestQueryDTO request, CriteriaBuilder criteriaBuilder, Root<ResidentEntity> from) {

        PredicateBuilder builder = new PredicateBuilder(criteriaBuilder, from);

        if (request.name() != null && !request.name().isEmpty()) {
            builder.addNamePredicates(request.name());
        }

        if (request.cpf() != null && !request.cpf().isEmpty()) {
            builder.addCpfPredicates(request.cpf());
        }

        if (request.phoneNumber() != null && !request.phoneNumber().isEmpty()) {
            builder.addPhoneNumberPredicates(request.phoneNumber());
        }

        if (request.email() != null && !request.email().isEmpty()) {
            builder.addEmailPredicates(request.email());
        }

        if (request.birthDate() != null && !request.birthDate().isEmpty()) {
            builder.addBirthDatePredicates(request.birthDate());
        }

        return builder.build();
    }

}
