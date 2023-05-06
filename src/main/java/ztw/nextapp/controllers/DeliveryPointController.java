package ztw.nextapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ztw.nextapp.domain.DeliveryPoint;
import ztw.nextapp.exceptions.IllegalOperationException;
import ztw.nextapp.services.DeliveryPointService;
import ztw.nextapp.web.model.DeliveryPointDto;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class DeliveryPointController {

    private final DeliveryPointService deliveryPointService;

    public DeliveryPointController(DeliveryPointService deliveryPointService) {
        this.deliveryPointService = deliveryPointService;
    }

    @GetMapping("/points")
    public ResponseEntity<List<DeliveryPointDto>> getDeliveryPointDto() {
        List<DeliveryPointDto> points = deliveryPointService.getPoints();
        if (points.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(points, HttpStatus.OK);
    }

    @GetMapping("/points/{id}")
    public ResponseEntity<DeliveryPointDto> getPointById(@PathVariable("id") long id) {
        DeliveryPointDto point;

        try {
            point = deliveryPointService.findPointById(id);
            return new ResponseEntity<>(point, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/points")
    public ResponseEntity<DeliveryPoint> createDeliveryPoint(@RequestBody DeliveryPointDto pointDto) {
        try {
            DeliveryPoint point = deliveryPointService.createDeliveryPoint(pointDto);
            return new ResponseEntity<>(point, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/points/{id}")
    public ResponseEntity<DeliveryPoint> updateDeliveryPoints(@PathVariable("id") long id, @RequestBody DeliveryPointDto pointDto) {
        DeliveryPoint pointData;

        try {
            deliveryPointService.updateDeliveryPoint(id, pointDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/points/{id}")
    public ResponseEntity<HttpStatus> deleteDeliveryPoint(@PathVariable("id") long id) {
        try {
            deliveryPointService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalOperationException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
