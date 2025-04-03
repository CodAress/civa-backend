package com.civa.platform.fleet.domain.model.commands;

public record CreateBusCommand(String number, Long brandId, String licensePlate, String features, String status) {
}
