package com.joaodebarro.resident.lib;

import com.joaodebarro.resident.entities.ResidentEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PredicateBuilder {
    private final CriteriaBuilder criteriaBuilder;
    private final Root<ResidentEntity> from;
    private final List<Predicate> predicates;

    public PredicateBuilder(CriteriaBuilder criteriaBuilder, Root<ResidentEntity> from) {
        this.criteriaBuilder = criteriaBuilder;
        this.from = from;
        this.predicates = new ArrayList<>();
    }

    public void addNamePredicates(List<String> names) {
        Expression<String> nameExpr = criteriaBuilder.lower(from.get("name"));
        List<Predicate> namePredicates = new ArrayList<>();
        for (String name : names) {
            namePredicates.add(criteriaBuilder.like(nameExpr, "%" + name.toLowerCase() + "%"));
        }
        predicates.add(criteriaBuilder.or(namePredicates.toArray(new Predicate[0])));
    }

    public void addCpfPredicates(List<String> cpfs) {
        Expression<Integer> cpfExpr = from.get("cpf");
        predicates.add(cpfExpr.in(cpfs));
    }

    public void addPhoneNumberPredicates(List<String> phoneNumbers) {
        Expression<String> phoneNumberExpr = criteriaBuilder.lower(from.get("phoneNumber"));
        List<Predicate> phoneNumberPredicates = new ArrayList<>();
        for (String phone : phoneNumbers) {
            phoneNumberPredicates.add(criteriaBuilder.like(phoneNumberExpr, "%" + phone.toLowerCase() + "%"));
        }
        predicates.add(criteriaBuilder.or(phoneNumberPredicates.toArray(new Predicate[0])));
    }

    public void addEmailPredicates(List<String> emails) {
        Expression<String> emailExpr = criteriaBuilder.lower(from.get("email"));
        List<Predicate> emailPredicates = new ArrayList<>();
        for (String email : emails) {
            emailPredicates.add(criteriaBuilder.like(emailExpr, "%" + email.toLowerCase() + "%"));
        }
        predicates.add(criteriaBuilder.or(emailPredicates.toArray(new Predicate[0])));
    }

    public void addBirthDatePredicates(List<LocalDate> birthDates) {
        Expression<LocalDate> birthDateExpr = from.get("birthDate");
        predicates.add(birthDateExpr.in(birthDates));
    }

    public Predicate build() {
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}