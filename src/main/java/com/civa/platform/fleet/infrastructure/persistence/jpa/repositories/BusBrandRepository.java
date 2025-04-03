package com.civa.platform.fleet.infrastructure.persistence.jpa.repositories;

import com.civa.platform.fleet.domain.model.entities.BusBrand;
import com.civa.platform.fleet.domain.model.valueobjects.BrandName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusBrandRepository extends JpaRepository<BusBrand, Long> {
    boolean existsByName(BrandName name);
}
