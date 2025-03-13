package com.github.divyanshtiwari001.traini8.specification;

import org.springframework.data.jpa.domain.Specification;

import com.github.divyanshtiwari001.traini8.model.Center;

import org.springframework.stereotype.Component;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class CenterSpecification {

    public Specification<Center> getFilteredCenters(String state, String city, String pincode,
                                                    String centerName, String centerCode) {
        return (Root<Center> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();  // Start with an empty predicate

            // Apply filters if provided
            if (state != null && !state.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("centerAddress").get("state")), "%" + state.toLowerCase() + "%"));
            }
            if (city != null && !city.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("centerAddress").get("city")), "%" + city.toLowerCase() + "%"));
            }
            if (pincode != null && !pincode.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("centerAddress").get("pincode"), "%" + pincode.toLowerCase() + "%"));
            }
            if (centerName != null && !centerName.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("centerName")), "%" + centerName.toLowerCase() + "%"));
            }
            if (centerCode != null && !centerCode.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("centerCode"), "%" + centerCode + "%"));
            }

            return predicate;  // Return the final predicate
        };
    }
}

