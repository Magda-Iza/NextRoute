package ztw.nextapp.controllers;

import org.springframework.stereotype.Controller;
import ztw.nextapp.services.DeliveryPointService;

@Controller
public class DeliveryPointController {

    private final DeliveryPointService deliveryPointService;

    public DeliveryPointController(DeliveryPointService deliveryPointService) {
        this.deliveryPointService = deliveryPointService;
    }
}
