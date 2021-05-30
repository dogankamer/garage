package com.dogankamer.service;

import com.dogankamer.controller.model.*;
import com.dogankamer.repository.model.Vehicle;

public interface GarageService {


    ParkResponse park (final ParkRequest parkRequest);
    LeaveResponse leave (final LeaveRequest leaveRequest);
    StatusResponse status();

}
