package ztw.nextapp.controllers;

import org.springframework.stereotype.Controller;
import ztw.nextapp.services.VehicleService;

@Controller
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
}
