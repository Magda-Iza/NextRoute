package ztw.nextapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ztw.nextapp.services.DeliveryService;
import ztw.nextapp.web.mapper.DeliveryMapper;
import ztw.nextapp.web.model.DeliveryDto;

@Controller
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final DeliveryMapper deliveryMapper;

    public DeliveryController(DeliveryService deliveryService, DeliveryMapper deliveryMapper) {
        this.deliveryService = deliveryService;
        this.deliveryMapper = deliveryMapper;
    }

    @GetMapping({"delivery", "delivery.html"})
    public String getDeliveries(Model model) {
        deliveryService.findAll();
        return "xyz";
    }

//    @PostMapping({"delivery", "delivery.html"})
//    public String saveDelivery(DeliveryDto deliveryDto, Model model) {
//        deliveryService.save(deliveryMapper.deliveryDtoToDelivery(deliveryDto));
//        return "xyz";
//    }
//
//    @PutMapping({"delivery", "delivery.html"})
//    public String updateDelivery(DeliveryDto deliveryDto, Model model) {
//        deliveryService.update(deliveryMapper.deliveryDtoToDelivery(deliveryDto));
//        return "xyz";
//    }
//
//    @DeleteMapping({"delivery", "delivery.html"})
//    public String deleteDelivery(DeliveryDto deliveryDto, Model model) {
//        deliveryService.delete(deliveryMapper.deliveryDtoToDelivery(deliveryDto));
//        return "xyz";
//    }
}
