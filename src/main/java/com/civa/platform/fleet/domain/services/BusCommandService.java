package com.civa.platform.fleet.domain.services;

import com.civa.platform.fleet.domain.model.agregates.Bus;
import com.civa.platform.fleet.domain.model.commands.CreateBusCommand;

import java.util.Optional;

public interface BusCommandService {
    Optional<Bus> handle(CreateBusCommand command);

}
