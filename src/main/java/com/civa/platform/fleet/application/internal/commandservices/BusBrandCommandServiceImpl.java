package com.civa.platform.fleet.application.internal.commandservices;

import com.civa.platform.fleet.domain.model.commands.CreateBusBrandCommand;
import com.civa.platform.fleet.domain.model.entities.BusBrand;
import com.civa.platform.fleet.domain.model.valueobjects.BrandName;
import com.civa.platform.fleet.domain.services.BusBrandCommandService;
import com.civa.platform.fleet.infrastructure.persistence.jpa.repositories.BusBrandRepository;
import com.civa.platform.shared.application.exceptions.ResourceAlreadyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusBrandCommandServiceImpl implements BusBrandCommandService {

    private final BusBrandRepository busBrandRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BusBrandCommandServiceImpl.class.getName());


    public BusBrandCommandServiceImpl(BusBrandRepository busBrandRepository) {
        this.busBrandRepository = busBrandRepository;
    }

    @Override
    public Optional<BusBrand> handle(CreateBusBrandCommand command) {
        if (busBrandRepository.existsByName(new BrandName(command.name()))) {
            LOGGER.error("Bus brand with same name already exists");
            throw new ResourceAlreadyException("Bus brand with same name already exists");
        }
        var busBrand = new BusBrand(new BrandName(command.name()));
        try {
            busBrandRepository.save(busBrand);
        } catch (Exception e) {
            LOGGER.error("Error while saving bus brand: {}", e.getMessage());
        }
        return Optional.of(busBrand);
    }
}
