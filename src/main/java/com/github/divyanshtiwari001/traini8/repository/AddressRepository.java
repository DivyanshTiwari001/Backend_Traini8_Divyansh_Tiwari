package com.github.divyanshtiwari001.traini8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.divyanshtiwari001.traini8.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
