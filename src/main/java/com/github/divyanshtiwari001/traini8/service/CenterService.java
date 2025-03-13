package com.github.divyanshtiwari001.traini8.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.github.divyanshtiwari001.traini8.exception.center.CenterNotFoundException;
import com.github.divyanshtiwari001.traini8.model.Center;
import com.github.divyanshtiwari001.traini8.repository.CenterRepository;
import com.github.divyanshtiwari001.traini8.specification.CenterSpecification;

@Service
public class CenterService {
    private CenterRepository repository;
    private CenterSpecification centerSpecification;

    public CenterService(CenterRepository repository, CenterSpecification centerSpecification) {
        this.repository = repository;
        this.centerSpecification = centerSpecification;
    }

    // service to create a center
    public Center newCenter(Center center) {
        return repository.save(center);
    }

    // service to get all centers
    public Page<Center> getFilteredCenters(String state, String city, String pincode,
                                           String centerName, String centerCode, Pageable pageable) {
        Specification<Center> specification = centerSpecification.getFilteredCenters(state, city, pincode, centerName, centerCode);

        // Query the repository with the specification and pageable
        return repository.findAll(specification, pageable);
    }

    // service to get single center by id
    public Center getCenterById(Long id) {
        Center center = repository.findById(id)
                .orElseThrow(() -> new CenterNotFoundException(id));
        return center;
    }
}

