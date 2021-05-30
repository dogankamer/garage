package com.dogankamer.controller.model;

import com.dogankamer.repository.model.Vehicle;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GarageStatusModel implements Serializable {
    private  Vehicle[] state;
}
