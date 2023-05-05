package ztw.nextapp.services;

import org.springframework.stereotype.Service;
import ztw.nextapp.domain.Vehicle;
import ztw.nextapp.exceptions.NotEnoughVehiclesException;
import ztw.nextapp.repository.VehicleRepository;
import ztw.nextapp.web.mapper.VehicleMapper;
import ztw.nextapp.web.model.VehicleDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.*;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public List<VehicleDto> findAll() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicleMapper::vehicleToVehicleDto)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDto findById(Long aLong) {
        return null;
    }

    @Override
    public VehicleDto save(Vehicle object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public List<VehicleDto> findVehiclesForRoute(Double load) {
        List<VehicleDto> allVehicles = findAll();
        List<VehicleDto> vehiclesForRoute;

        vehiclesForRoute = findVehiclesForRouteHelper(load, allVehicles);

        return vehiclesForRoute;
    }


    private VehicleDto findFirstVehicleWithCapacity(Double load, List<VehicleDto> vehicles){
        int left = 0;
        int right = vehicles.size() - 1;
        int mid;

        while (left < right) {
            mid = (left + right) / 2;

            if (vehicles.get(mid).getCapacity() < load) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return vehicles.get(left);
    }

    public List<VehicleDto> findVehiclesForRouteHelper(Double load, List<VehicleDto> vehicles) {
        vehicles.sort(Comparator.comparing(VehicleDto::getCapacity));
        List<VehicleDto> vehiclesForRoute = new ArrayList<>();
        Double currentLoad = load;

        while (currentLoad > 0 && vehicles.size() > 0) {
            VehicleDto vehicle = findFirstVehicleWithCapacity(currentLoad, vehicles);

            if (vehicle.getCapacity() >= currentLoad) {
                vehiclesForRoute.add(vehicle);
                currentLoad = 0.0;
            } else {
                vehiclesForRoute.add(vehicle);
                currentLoad -= vehicle.getCapacity();
                vehicles.remove(vehicle);
            }
        }

        if (currentLoad > 0) {
            throw new NotEnoughVehiclesException("Not enough vehicles for this route - load bigger than available vehicles capacity");
        }

        return vehiclesForRoute;
    }
}
