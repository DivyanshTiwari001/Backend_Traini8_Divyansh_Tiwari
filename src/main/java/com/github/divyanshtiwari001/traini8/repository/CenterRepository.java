package com.github.divyanshtiwari001.traini8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.github.divyanshtiwari001.traini8.model.Center;


@Repository
public interface CenterRepository extends JpaRepository<Center, Long>,JpaSpecificationExecutor<Center> {
    
}
