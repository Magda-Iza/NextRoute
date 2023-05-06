package ztw.nextapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ztw.nextapp.domain.Vehicle;
import ztw.nextapp.exceptions.IllegalOperationException;
import ztw.nextapp.services.VehicleService;
import ztw.nextapp.web.model.VehicleDto;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleDto>> getVehicles(Model model) {
        List<VehicleDto> vehicles = vehicleService.findAll();
        if (vehicles.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable("id") long id) {
        VehicleDto vehicle;

        try {
            vehicle = vehicleService.findById(id);
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody VehicleDto vehicleDTO) {
        try {
            Vehicle vehicle = vehicleService.createVehicle(vehicleDTO);
            return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") long id, @RequestBody VehicleDto vehicleDto) {
        Vehicle vehicleData;

        try {
            vehicleService.updateVehicle(id, vehicleDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<HttpStatus> deleteVehicle(@PathVariable("id") long id) {
        try {
            vehicleService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalOperationException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
