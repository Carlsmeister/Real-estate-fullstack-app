package com.SE.real_estate_prices.residence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Integer> {
    void deleteByResidenceAddressId(Integer residenceAddressId);
    Optional<Residence> findByResidenceAddressId(Integer residenceAddressId);
}
