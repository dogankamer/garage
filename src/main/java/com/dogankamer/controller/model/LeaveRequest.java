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
public class LeaveRequest implements Serializable {
    @NotNull
    private Integer ticket;
}
