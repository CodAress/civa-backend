package com.civa.platform.fleet.domain.services;

import com.civa.platform.fleet.domain.model.agregates.Bus;
import com.civa.platform.fleet.domain.model.queries.GetAllBusesQuery;
import com.civa.platform.fleet.domain.model.queries.GetBusByIdQuery;

import java.util.List;
import java.util.Optional;

public interface BusQueryService {
    Optional<Bus> handle(GetBusByIdQuery query);
    List<Bus> handle(GetAllBusesQuery query);
}
