package com.civa.platform.fleet.application.internal.queryservices;

import com.civa.platform.fleet.domain.model.agregates.Bus;
import com.civa.platform.fleet.domain.model.queries.GetAllBusesQuery;
import com.civa.platform.fleet.domain.model.queries.GetBusByIdQuery;
import com.civa.platform.fleet.domain.services.BusQueryService;
import com.civa.platform.fleet.infrastructure.persistence.jpa.repositories.BusRepository;
import com.civa.platform.shared.application.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusQueryServiceImpl implements BusQueryService {

    private final BusRepository busRepository;
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BusQueryServiceImpl.class);

    public BusQueryServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public Optional<Bus> handle(GetBusByIdQuery query) {
        return Optional.ofNullable(busRepository.findById(query.id()).orElseThrow(() -> {
            LOGGER.error("Bus with id {} not found", query.id());
            return new RuntimeException("Bus not found");
        }));
    }

    @Override
    public List<Bus> handle(GetAllBusesQuery query) {
        var buses = busRepository.findAll();
        if(buses.isEmpty())
        {
            LOGGER.error("No buses found");
            throw new ResourceNotFoundException("No buses found");
        }

        LOGGER.info("Found {} buses", buses.size());
        return buses;
    }
}
