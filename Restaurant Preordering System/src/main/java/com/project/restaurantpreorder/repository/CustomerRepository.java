package com.project.restaurantpreorder.repository;

import com.project.restaurantpreorder.model.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerProfile, Long> {
}
