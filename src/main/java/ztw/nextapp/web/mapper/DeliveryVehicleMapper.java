package ztw.nextapp.web.mapper;

import org.springframework.stereotype.Component;
import ztw.nextapp.domain.DeliveryPoint;
import ztw.nextapp.domain.DeliveryVehicle;
import ztw.nextapp.domain.Person;
import ztw.nextapp.domain.Vehicle;
import ztw.nextapp.web.model.DeliveryPointDto;
import ztw.nextapp.web.model.DeliveryVehicleDto;

@Component
public class DeliveryVehicleMapper {

    public DeliveryVehicleDto deliveryVehicleToDeliveryVehicleDto(DeliveryVehicle deliveryVehicle) {
        DeliveryVehicleDto deliveryVehicleDto = new DeliveryVehicleDto();
        Vehicle vehicle = deliveryVehicle.getVehicle();
        Person driver = deliveryVehicle.getPerson();
        deliveryVehicleDto.setId(vehicle.getId());
        deliveryVehicleDto.setVehicle(vehicle.getVin());
        deliveryVehicleDto.setDriver(driver.getName());

        return deliveryVehicleDto;
    }
}
