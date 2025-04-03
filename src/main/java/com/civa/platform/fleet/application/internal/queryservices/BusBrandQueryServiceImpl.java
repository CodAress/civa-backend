package com.civa.platform.fleet.application.internal.queryservices;

import com.civa.platform.fleet.domain.model.entities.BusBrand;
import com.civa.platform.fleet.domain.model.queries.GetAllBusBrandsQuery;
import com.civa.platform.fleet.domain.services.BusBrandQueryService;
import com.civa.platform.fleet.infrastructure.persistence.jpa.repositories.BusBrandRepository;
import com.civa.platform.shared.application.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusBrandQueryServiceImpl implements BusBrandQueryService {

    private final BusBrandRepository busBrandRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BusBrandQueryServiceImpl.class);

    public BusBrandQueryServiceImpl(BusBrandRepository busBrandRepository) {
        this.busBrandRepository = busBrandRepository;
    }


    @Override
    public List<BusBrand> handle(GetAllBusBrandsQuery query) {

        try {
            var brands = busBrandRepository.findAll();
            if(brands.isEmpty()) throw new ResourceNotFoundException("No brands found");
            return brands;
        }
        catch (Exception e) {
            LOGGER.error("Error while fetching brands: {}", e.getMessage());
            throw new RuntimeException("Error while fetching brands");
        }


    }
}
