package com.dogankamer.service.impl;

import com.dogankamer.controller.model.*;
import com.dogankamer.repository.Garage;
import com.dogankamer.repository.model.Vehicle;
import com.dogankamer.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.UUID;

@Service
public class GarageServiceImpl implements GarageService {
    @Autowired
    private Garage garage;

    @Override
    public ParkResponse park(ParkRequest parkRequest) {

        Vehicle vehicle = parkRequest.toVehicle();
        Integer ticket = garage.placeCar(vehicle);
        if (ticket != null) {
            int width = vehicle.getType().getWidth();
            return ParkResponse
                    .builder()
                    .ticket(ticket)
                    .message(MessageFormat
                            .format("Allocated {0} slot{1}"
                                    , width, width > 1 ? "s" : "")).build();
        } else {
            ParkResponse.builder().message("Garage is full").build();
        }
        return null;
    }

    @Override
    public LeaveResponse leave(LeaveRequest leaveRequest) {
        Vehicle vehicle = garage.leaveCar(leaveRequest.getTicket());
        if (vehicle != null) {
            return LeaveResponse.builder().vehicle(vehicle).build();
        } else {
            return LeaveResponse.builder().build();
        }
    }

    @Override
    public StatusResponse status() {
        return garage.getStatus();
    }
}
