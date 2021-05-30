package com.dogankamer.repository.model;

import com.dogankamer.controller.model.VehicleType;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Vehicle {
    @Size(min = 1, max=4000)
    private String plate;
    @Size(min = 1, max=4000)
    private String colour;
    @NotNull
    private VehicleType type;
    private Integer ticket;
    private int index;
}
