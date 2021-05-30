package com.dogankamer.controller.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusResponse {

    private List<ParkedVehicle> list;
}
