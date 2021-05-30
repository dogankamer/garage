package com.dogankamer.controller.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkedVehicle {
    private String plate;
    private String colour;
    private Integer ticket;
    private int[] slots;

}
