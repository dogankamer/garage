package com.dogankamer.controller.model;

import com.dogankamer.repository.model.Vehicle;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkRequest implements Serializable {
    @Size(min = 1, max = 4000)
    private String plate;
    @Size(min = 1, max = 4000)
    private String colour;
    @NotNull
    private VehicleType type;

    public Vehicle toVehicle() {
        return Vehicle.builder().plate(plate)
                .colour(colour)
                .type(type).build();
    }
}
