package com.civa.platform.fleet.interfaces.rest;

import com.civa.platform.fleet.domain.model.queries.GetAllBusBrandsQuery;
import com.civa.platform.fleet.domain.services.BusBrandCommandService;
import com.civa.platform.fleet.domain.services.BusBrandQueryService;
import com.civa.platform.fleet.interfaces.rest.resources.BusBrandResource;
import com.civa.platform.fleet.interfaces.rest.resources.CreateBusBrandResource;
import com.civa.platform.fleet.interfaces.rest.transform.BusBrandResourceFromEntityAssembler;
import com.civa.platform.fleet.interfaces.rest.transform.CreateBusBrandCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/bus-brands", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Bus Brands", description = "Bus Brand Management Endpoints")
public class BusBrandController {
    private final BusBrandCommandService busBrandCommandService;
    private final BusBrandQueryService busBrandQueryService;

    public BusBrandController(BusBrandCommandService busBrandCommandService, BusBrandQueryService busBrandQueryService) {
        this.busBrandCommandService = busBrandCommandService;
        this.busBrandQueryService = busBrandQueryService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BusBrandResource> createBusBrand(@RequestBody CreateBusBrandResource resource) {
        var createBusBrandCommand = CreateBusBrandCommandFromResourceAssembler.toCommandFromResource(resource);
        var busBrand = busBrandCommandService.handle(createBusBrandCommand);
        var busBrandResource = BusBrandResourceFromEntityAssembler.toResourceFromEntity(busBrand.get());
        return new ResponseEntity<>(busBrandResource, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BusBrandResource>> getAllBusBrands() {
        var busBrands = busBrandQueryService.handle(new GetAllBusBrandsQuery());
        var busBrandResources = busBrands.stream().map(BusBrandResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(busBrandResources, HttpStatus.OK);
    }
}
