package ztw.nextapp.services;

import ztw.nextapp.domain.Vehicle;
import ztw.nextapp.exceptions.IllegalOperationException;
import ztw.nextapp.exceptions.NotEnoughVehiclesException;
import ztw.nextapp.web.model.DeliveryVehicleDto;
import ztw.nextapp.web.model.VehicleDto;
import java.util.List;

public interface VehicleService {
    List<VehicleDto> findAll();

    VehicleDto findById(Long id);

    VehicleDto save(Vehicle object);

    void delete(Long id);

    void deleteById(Long id) throws IllegalOperationException;
    public List<VehicleDto> findVehiclesForRoute(Double capacity) throws NotEnoughVehiclesException;

    Vehicle createVehicle(VehicleDto vehicleDTO);

    void updateVehicle(long id, VehicleDto vehicleDto);

    List<VehicleDto> findVehiclesByDeliveryId(Long id);

    List<DeliveryVehicleDto>  findVehiclesInDelivery(Long id);
}
