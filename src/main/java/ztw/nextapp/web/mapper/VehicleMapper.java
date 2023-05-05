package ztw.nextapp.web.mapper;

import org.springframework.stereotype.Component;
import ztw.nextapp.domain.Vehicle;
import ztw.nextapp.web.model.VehicleDto;

@Component
public class VehicleMapper {
    public Vehicle vehicleDtoToVehicle(VehicleDto vehicleDto) {
        return Vehicle.builder()
                .id(vehicleDto.getId())
                .vin(vehicleDto.getVin())
                .brand(vehicleDto.getBrand())
                .model(vehicleDto.getModel())
                .capacity(vehicleDto.getCapacity())
                .registerDate(vehicleDto.getRegisterDate())
                .build();
    }

    public VehicleDto vehicleToVehicleDto(Vehicle vehicle) {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(vehicle.getId());
        vehicleDto.setVin(vehicle.getVin());
        vehicleDto.setBrand(vehicle.getBrand());
        vehicleDto.setModel(vehicle.getModel());
        vehicleDto.setCapacity(vehicle.getCapacity());
        vehicleDto.setRegisterDate(vehicle.getRegisterDate());
        return vehicleDto;
    }
}
