package com.joaodebarro.resident.domain.repositories.impl;

import com.joaodebarro.resident.domain.dtos.ResidentRequestDTO;
import com.joaodebarro.resident.domain.dtos.ResidentResponseDTO;
import com.joaodebarro.resident.domain.entities.ResidentEntity;
import com.joaodebarro.resident.domain.entities.ResidentialUnitEntity;
import com.joaodebarro.resident.domain.interfaces.ResidentRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ResidentRepositoryImpl implements ResidentRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public Optional<List<ResidentEntity>> findAllResidents(ResidentRequestDTO residentRequestDTO, Pageable pageable)
    {

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<ResidentEntity> query = criteriaBuilder.createQuery(ResidentEntity.class);

        Root<ResidentEntity> root = query.from(ResidentEntity.class);
        Fetch<ResidentEntity, ResidentialUnitEntity> residentialUnitFetch = root.fetch("residentialUnit",JoinType.LEFT);


        ArrayList<Predicate> predicates = new ArrayList<>();

        Expression<String> residentNameLowercaseExpression = criteriaBuilder.lower(criteriaBuilder.literal("%"+residentRequestDTO.name()+"%"));
        Predicate residentNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),residentNameLowercaseExpression);

        Expression<String> residentCPFExpression = criteriaBuilder.lower(criteriaBuilder.literal(residentRequestDTO.cpf()));
        Predicate residentCPFPredicate = criteriaBuilder.equal(criteriaBuilder.lower(root.get("cpf")),residentCPFExpression);

        predicates.add(residentNamePredicate);
        predicates.add(residentCPFPredicate);

        query.where(predicates.toArray(new Predicate[predicates.size()])); // uma das formas de converter um list em um array



        TypedQuery<ResidentEntity> result = manager.createQuery(query);
        result.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        result.setMaxResults(pageable.getPageSize());
        if (result.getResultList().isEmpty()) return Optional.empty();
        return Optional.of(result.getResultList());

    }

}
