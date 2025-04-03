package com.civa.platform.fleet.domain.services;

import com.civa.platform.fleet.domain.model.commands.CreateBusBrandCommand;
import com.civa.platform.fleet.domain.model.entities.BusBrand;

import java.util.Optional;

public interface BusBrandCommandService {
    Optional<BusBrand> handle(CreateBusBrandCommand command);
}
