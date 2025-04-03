package com.civa.platform.fleet.interfaces.rest.resources;

public record CreateBusResource(String number, String licensePlate, Long brandId, String features, String status) {
}
