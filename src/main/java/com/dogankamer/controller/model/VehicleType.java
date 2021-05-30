package com.dogankamer.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum VehicleType {
    Car(1), Jeep(2), Truck(4);
    @Getter
    private int width;

}
