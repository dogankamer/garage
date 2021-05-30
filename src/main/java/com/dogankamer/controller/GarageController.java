package com.dogankamer.controller;

import com.dogankamer.controller.model.*;
import com.dogankamer.repository.model.Vehicle;
import com.dogankamer.service.GarageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequestMapping("/garage")
@SecurityRequirement(name = "bearerAuth")
public class GarageController {
    @Autowired
    private GarageService garageService;

    @PostMapping
    public ParkResponse park(@RequestBody ParkRequest parkRequest) {
        return garageService.park(parkRequest);
    }
    @DeleteMapping("{ticket}")
    public LeaveResponse leave(@PathVariable @NotNull Integer ticket) {
        return garageService.leave(LeaveRequest.builder().ticket(ticket).build());
    }
    @GetMapping
    public StatusResponse status () {

        return garageService.status();
    }
}
