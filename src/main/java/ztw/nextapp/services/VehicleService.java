package ztw.nextapp.services;

import ztw.nextapp.domain.Vehicle;
import ztw.nextapp.exceptions.IllegalOperationException;
import ztw.nextapp.web.model.VehicleDto;
import java.util.List;
import java.util.Set;

public interface VehicleService {
    List<VehicleDto> findAll();

    VehicleDto findById(Long id);

    VehicleDto save(Vehicle object);

    void delete(Long id);

    void deleteById(Long id) throws IllegalOperationException;
    public List<VehicleDto> findVehiclesForRoute(Double capacity);

    Vehicle createVehicle(VehicleDto vehicleDTO);

    void updateVehicle(long id, VehicleDto vehicleDto);

    List<VehicleDto> findVehiclesByDeliveryId(Long id);
}
