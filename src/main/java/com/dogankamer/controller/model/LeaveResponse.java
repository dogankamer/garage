package com.dogankamer.controller.model;

import com.dogankamer.repository.model.Vehicle;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveResponse implements Serializable {
    private Vehicle vehicle;
}
