package ztw.nextapp.services;

import org.springframework.stereotype.Service;
import ztw.nextapp.domain.Vehicle;
import ztw.nextapp.exceptions.IllegalOperationException;
import ztw.nextapp.exceptions.NotEnoughVehiclesException;
import ztw.nextapp.repositories.VehicleRepository;
import ztw.nextapp.web.mapper.VehicleMapper;
import ztw.nextapp.web.model.VehicleDto;

import java.util.*;
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
        return vehicleMapper.vehicleToVehicleDto(vehicleRepository.findById(aLong).get());
    }

    @Override
    public VehicleDto save(Vehicle object) {
        return vehicleMapper.vehicleToVehicleDto(vehicleRepository.save(object));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteById(Long id) throws IllegalOperationException {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isPresent() != false)
            vehicleRepository.deleteById(id);
        else
            throw new IllegalOperationException();
    }

    @Override
    public Vehicle createVehicle(VehicleDto vehicleDTO) {
        Vehicle vehicle = vehicleMapper.vehicleDtoToVehicle(vehicleDTO);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    @Override
    public void updateVehicle(long id, VehicleDto vehicleDto) {
        Optional<Vehicle> vehicleData = vehicleRepository.findById(id);

        if (vehicleData.isPresent()) {
            Vehicle _vehicle = vehicleData.get();
            _vehicle.setBrand(vehicleDto.getBrand());
            _vehicle.setModel(vehicleDto.getModel());
            _vehicle.setCapacity(vehicleDto.getCapacity());
            _vehicle.setVin(vehicleDto.getVin());
            _vehicle.setRegisterDate(vehicleDto.getRegisterDate());

            vehicleRepository.save(_vehicle);
        } else {
            throw new NoSuchElementException("Vehicle with id '" + id + "' does not exist");
        }
    }

    @Override
    public List<VehicleDto> findVehiclesByDeliveryId(Long id) {
        //TODO
        return new ArrayList<>();
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
