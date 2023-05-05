package ztw.nextapp.services;

import ztw.nextapp.domain.Vehicle;
import ztw.nextapp.web.model.VehicleDto;
import java.util.List;
import java.util.Set;

public interface VehicleService {
    List<VehicleDto> findAll();

    VehicleDto findById(Long id);

    VehicleDto save(Vehicle object);

    void delete(Long id);

    void deleteById(Long id);
    public List<VehicleDto> findVehiclesForRoute(Double capacity);
}
