package com.civa.platform.fleet.application.internal.commandservices;

import com.civa.platform.fleet.domain.model.agregates.Bus;
import com.civa.platform.fleet.domain.model.commands.CreateBusCommand;
import com.civa.platform.fleet.domain.model.valueobjects.BusNumber;
import com.civa.platform.fleet.domain.model.valueobjects.BusStatus;
import com.civa.platform.fleet.domain.model.valueobjects.LicensePlate;
import com.civa.platform.fleet.domain.services.BusCommandService;
import com.civa.platform.fleet.infrastructure.persistence.jpa.repositories.BusBrandRepository;
import com.civa.platform.fleet.infrastructure.persistence.jpa.repositories.BusRepository;
import com.civa.platform.shared.application.exceptions.InvalidValueException;
import com.civa.platform.shared.application.exceptions.ResourceAlreadyException;
import com.civa.platform.shared.application.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusCommandServiceImpl implements BusCommandService {

    private final BusRepository busRepository;
    private final BusBrandRepository busBrandRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BusCommandServiceImpl.class);

    public BusCommandServiceImpl(BusRepository busRepository, BusBrandRepository busBrandRepository) {
        this.busRepository = busRepository;
        this.busBrandRepository = busBrandRepository;
    }

    @Override
    public Optional<Bus> handle(CreateBusCommand command) {
        if(busRepository.existsByNumber(new BusNumber(command.number()))) {
            throw new ResourceAlreadyException("Bus with number " + command.number() + " already exists");
        }
        if(busRepository.existsByLicensePlate(new LicensePlate(command.licensePlate()))) {
            throw new ResourceAlreadyException("Bus with license plate " + command.licensePlate() + " already exists");
        }

        if(!BusStatus.isValidEnum(command.status()))
        {
            throw new InvalidValueException("Invalid bus status");
        }

        var busBrand = busBrandRepository.findById(command.brandId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus brand not found"));

        var busStatus = BusStatus.fromString(command.status());
        var bus = new Bus(new BusNumber(command.number()), new LicensePlate(command.licensePlate()), busBrand, busStatus);

        try {
            busRepository.save(bus);
        } catch (Exception e) {
            LOGGER.error("Error while saving bus: {}", e.getMessage());
            return Optional.empty();
        }
        return Optional.of(bus);
    }
}
