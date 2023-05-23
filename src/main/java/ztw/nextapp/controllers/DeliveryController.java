package ztw.nextapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ztw.nextapp.domain.Delivery;
import ztw.nextapp.domain.DeliveryPoint;
import ztw.nextapp.exceptions.IllegalOperationException;
import ztw.nextapp.services.DeliveryService;
import ztw.nextapp.services.RouteService;
import ztw.nextapp.web.mapper.DeliveryMapper;
import ztw.nextapp.web.model.DeliveryDto;
import ztw.nextapp.web.model.DeliveryPointDto;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final DeliveryMapper deliveryMapper;
    private final RouteService routeService;

    public DeliveryController(DeliveryService deliveryService, DeliveryMapper deliveryMapper, RouteService routeService) {
        this.deliveryService = deliveryService;
        this.deliveryMapper = deliveryMapper;
        this.routeService = routeService;
    }

    @GetMapping("employee/deliveries")
    public ResponseEntity<List<DeliveryDto>> getEmployeeDeliveries() {
        try {
            List<DeliveryDto> deliveries = deliveryService.getDeliveries();

            if (deliveries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(deliveries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("admin/deliveries")
    public ResponseEntity<List<DeliveryDto>> getAdminDeliveries() {
        try {
            List<DeliveryDto> deliveries = deliveryService.getDeliveries();

            if (deliveries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(deliveries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("driver/deliveries")
    public ResponseEntity<List<DeliveryDto>> getDriverDeliveries() {
        try {
            List<DeliveryDto> deliveries = deliveryService.getDriverDeliveries(1L);

            if (deliveries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(deliveries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/deliveries/{id}")
    public ResponseEntity<DeliveryDto> getDeliveryById(@PathVariable("id") long id) {
        DeliveryDto delivery;

        try {
            delivery = deliveryService.findDeliveryById(id);
            return new ResponseEntity<>(delivery, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("deliveries")
    public ResponseEntity<Delivery> saveDelivery(DeliveryDto deliveryDto) {
        try {
            Delivery delivery = deliveryService.createDelivery(deliveryDto);
            return new ResponseEntity<>(delivery, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("deliveries/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable("id") long id, @RequestBody DeliveryDto deliveryDto) {
        Delivery deliveryData;

        try {
            deliveryService.updateDelivery(id, deliveryDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deliveries/{id}")
    public ResponseEntity<HttpStatus> deleteDelivery(@PathVariable("id") long id) {
        try {
            deliveryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalOperationException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/deliveries/route/points/{id}")
    public ResponseEntity<DeliveryPoint> createRoutePoint(@RequestBody String address, @PathVariable String id) {
        try {
            routeService.addRoutePoint(Long.parseLong(id), address);

            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

}
