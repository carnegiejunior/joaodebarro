package com.joaodebarro.resident;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ResidentRepositoryImpl implements ResidentRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<ResidentEntity>> findAllResidents(ResidentRequestQueryDTO request, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ResidentEntity> cq = cb.createQuery(ResidentEntity.class);
        Root<ResidentEntity> root = cq.from(ResidentEntity.class);
        root.fetch("residentialUnit", JoinType.LEFT);
        cq.select(root);
        cq.where(getPredicate(request, cb, root));
        List<ResidentEntity> result = entityManager.createQuery(cq).getResultList();
        return Optional.of(result);
    }

    private static Predicate getPredicate(ResidentRequestQueryDTO request, CriteriaBuilder cb, Root<ResidentEntity> root) {

        Predicate predicate = cb.conjunction();

        if (request.name()!=null && !request.name().isEmpty()) {
            List<String> names = request.name().stream().toList();
            if (!names.isEmpty()) {
                Expression<String> nameExpr = cb.lower(root.get("name"));
                List<Predicate> namePredicates = new ArrayList<>();
                for (String name : names) {
                    namePredicates.add(cb.like(nameExpr, "%" + name.toLowerCase() + "%"));
                }
                predicate = cb.and(predicate, cb.or(namePredicates.toArray(new Predicate[0])));
            }
        }

        if (request.email()!=null && !request.email().isEmpty()) {
            List<String> cpfs = request.cpf().stream().toList();
            if (!cpfs.isEmpty()) {
                Expression<Integer> cpfExpr = root.get("cpf");
                predicate = cb.and(predicate, cpfExpr.in(cpfs));
            }
        }

        return predicate;
    }
}
