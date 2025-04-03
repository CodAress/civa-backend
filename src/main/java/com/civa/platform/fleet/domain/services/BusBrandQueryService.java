package com.civa.platform.fleet.domain.services;

import com.civa.platform.fleet.domain.model.entities.BusBrand;
import com.civa.platform.fleet.domain.model.queries.GetAllBusBrandsQuery;

import java.util.List;

public interface BusBrandQueryService {
    List<BusBrand> handle(GetAllBusBrandsQuery query);
}
