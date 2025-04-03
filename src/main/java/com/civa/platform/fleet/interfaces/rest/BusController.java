package com.civa.platform.fleet.interfaces.rest;

import com.civa.platform.fleet.domain.model.queries.GetAllBusesQuery;
import com.civa.platform.fleet.domain.model.queries.GetBusByIdQuery;
import com.civa.platform.fleet.domain.services.BusCommandService;
import com.civa.platform.fleet.domain.services.BusQueryService;
import com.civa.platform.fleet.interfaces.rest.resources.BusResource;
import com.civa.platform.fleet.interfaces.rest.resources.CreateBusResource;
import com.civa.platform.fleet.interfaces.rest.transform.BusResourceFromEntityAssembler;
import com.civa.platform.fleet.interfaces.rest.transform.CreateBusCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/buses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Bus", description = "Bus Management Endpoints")
public class BusController {
    private final BusCommandService busCommandService;
    private final BusQueryService busQueryService;

    public BusController(BusCommandService busCommandService, BusQueryService busQueryService) {
        this.busCommandService = busCommandService;
        this.busQueryService = busQueryService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BusResource> createBus(@RequestBody CreateBusResource resource) {
        var createBusCommand = CreateBusCommandFromResourceAssembler.toCommandFromResource(resource);
        var bus = busCommandService.handle(createBusCommand);
        var busResource = BusResourceFromEntityAssembler.toResourceFromEntity(bus.get());
        return new ResponseEntity<>(busResource, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{busId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BusResource> getBusById(@PathVariable Long busId) {
        var getBusByIdQuery = new GetBusByIdQuery(busId);
        var bus = busQueryService.handle(getBusByIdQuery);
        var busResource = BusResourceFromEntityAssembler.toResourceFromEntity(bus.get());
        return new ResponseEntity<>(busResource, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BusResource>> getAllBuses() {
        var buses = busQueryService.handle(new GetAllBusesQuery());
        var busResources = buses.stream().map(BusResourceFromEntityAssembler::toResourceFromEntity).toList();
        return new ResponseEntity<>(busResources, HttpStatus.OK);
    }
}
