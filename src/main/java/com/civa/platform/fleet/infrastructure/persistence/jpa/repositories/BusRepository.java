package com.civa.platform.fleet.infrastructure.persistence.jpa.repositories;

import com.civa.platform.fleet.domain.model.agregates.Bus;
import com.civa.platform.fleet.domain.model.valueobjects.BusNumber;
import com.civa.platform.fleet.domain.model.valueobjects.LicensePlate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsByNumber (BusNumber number);
    boolean existsByLicensePlate (LicensePlate licensePlate);
}
