package com.civa.platform.fleet.domain.model.agregates;

import com.civa.platform.fleet.domain.model.entities.BusBrand;
import com.civa.platform.fleet.domain.model.valueobjects.BusNumber;
import com.civa.platform.fleet.domain.model.valueobjects.BusStatus;
import com.civa.platform.fleet.domain.model.valueobjects.LicensePlate;
import com.civa.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Bus extends AuditableAbstractAggregateRoot<Bus> {

    @Embedded
    private BusNumber number;

    @Embedded
    private LicensePlate licensePlate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private BusBrand busBrand;

    private BusStatus status;

    public Bus() {

    }

    public Bus(BusNumber number, LicensePlate licensePlate, BusBrand busBrand, BusStatus status) {
        this.number = number;
        this.licensePlate = licensePlate;
        this.busBrand = busBrand;
        this.status = status;
    }

}
