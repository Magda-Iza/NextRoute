package ztw.nextapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ztw.nextapp.domain.Delivery;
import ztw.nextapp.domain.DeliveryPoint;
import ztw.nextapp.exceptions.IllegalOperationException;
import ztw.nextapp.exceptions.NotEnoughVehiclesException;
import ztw.nextapp.services.DeliveryService;
import ztw.nextapp.services.RouteService;
import ztw.nextapp.services.VehicleService;
import ztw.nextapp.web.mapper.DeliveryMapper;
import ztw.nextapp.web.model.DeliveryDto;
import ztw.nextapp.web.model.DeliveryPointDto;
import ztw.nextapp.web.model.DeliveryVehicleDto;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final DeliveryMapper deliveryMapper;
    private final RouteService routeService;
    private final VehicleService vehicleService;

    public DeliveryController(DeliveryService deliveryService, DeliveryMapper deliveryMapper, RouteService routeService, VehicleService vehicleService) {
        this.deliveryService = deliveryService;
        this.deliveryMapper = deliveryMapper;
        this.routeService = routeService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("employee/deliveries/{name}")
    public ResponseEntity<List<DeliveryDto>> getEmployeeDeliveries(@PathVariable("name") String name) {
        System.out.print(name);
        try {
            List<DeliveryDto> deliveries = deliveryService.getEmployeeDeliveries(name);

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

            return new ResponseEntity<>(deliveries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="driver/deliveries/{name}")
    public ResponseEntity<List<DeliveryDto>> getDriverDeliveries(@PathVariable("name") String name) {
        System.out.print(name);
        try {
            List<DeliveryDto> deliveries = deliveryService.getDriverDeliveries(name);

            if (deliveries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(deliveries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value="admin/delivery/{id}")
    public ResponseEntity<DeliveryDto> getDeliveryById(@PathVariable("id") long id) {
        DeliveryDto delivery;

        try {
            delivery = deliveryService.findDeliveryById(id);
            return new ResponseEntity<>(delivery, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="deliveries/{id}/{name}")
    public ResponseEntity<DeliveryDto>  assignEmployeeToDelivery(@PathVariable("id") String id, @PathVariable("name") String name) {
        DeliveryDto deliveryDto;


        deliveryDto = deliveryService.assignEmployeeToDelivery(Long.valueOf(id), name);
        return new ResponseEntity<>(deliveryDto, HttpStatus.OK);

    }

    @GetMapping("employee/delivery/{id}")
    public ResponseEntity<DeliveryDto> getDeliveryEmployeeById(@PathVariable("id") long id) {
        DeliveryDto delivery;

        try {
            delivery = deliveryService.findDeliveryById(id);
            return new ResponseEntity<>(delivery, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="driver/delivery/{id}")
    public ResponseEntity<DeliveryDto> getDeliveryDriverById(@PathVariable("id") long id) {
        DeliveryDto delivery;
        //System.out.print(name);
        try {
            delivery = deliveryService.findDeliveryById(id);
            return new ResponseEntity<>(delivery, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("deliveries")
    public ResponseEntity<Delivery> saveDelivery(@RequestBody DeliveryDto delivery) {
        try {
            Delivery deliveryResult = deliveryService.createDelivery(delivery);
            return new ResponseEntity<>(deliveryResult, HttpStatus.CREATED);
        } catch (IllegalOperationException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        } catch (NotEnoughVehiclesException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    @GetMapping("transport/{id}")
    public ResponseEntity<List<DeliveryVehicleDto>> getDeliveryVehicles(@PathVariable("id") Long id) {
        return new ResponseEntity<>(vehicleService.findVehiclesInDelivery(id), HttpStatus.OK);
    }

    @PostMapping("deliveries/{id}/route/points")
    public ResponseEntity<DeliveryPoint> createRoutePoint(@RequestBody String address, @PathVariable String id) {
        try {
            routeService.addRoutePoint(Long.parseLong(id), address);

            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("deliveries/new")
    public ResponseEntity<DeliveryDto> getNewDelivery() {
        try {
            DeliveryDto delivery = deliveryService.getNewDelivery();

            System.out.println("Delivery id: ");
            System.out.println(delivery.getId());
            return new ResponseEntity<>(delivery, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("deliveries/route/points/{id}")
    public ResponseEntity<List<DeliveryPointDto>> getRoutePoints(@PathVariable String id) {
        try {
            List<DeliveryPointDto> deliveryPoints = deliveryService.getDeliveryRoutePoints(Long.parseLong(id));

            return new ResponseEntity<>(deliveryPoints, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("deliveries/{deliveryId}/route/points/{pointId}")
    public ResponseEntity<HttpStatus> deleteRoutePoint(@PathVariable String deliveryId, @PathVariable String pointId) {
        try {
            routeService.deleteRoutePoint(Long.parseLong(deliveryId), Long.parseLong(pointId));
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
