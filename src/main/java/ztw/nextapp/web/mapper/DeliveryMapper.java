package ztw.nextapp.web.mapper;

import org.springframework.stereotype.Component;
import ztw.nextapp.domain.Delivery;
import ztw.nextapp.repositories.PersonRepository;
import ztw.nextapp.repositories.VehicleRepository;
import ztw.nextapp.web.model.DeliveryDto;

@Component
public class DeliveryMapper {
    PersonRepository personRepository;
    VehicleRepository vehicleRepository;
    PersonMapper personMapper;

    public DeliveryMapper(PersonRepository personRepository, VehicleRepository vehicleRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.vehicleRepository = vehicleRepository;
        this.personMapper = personMapper;
    }

    public Delivery deliveryDtoToDelivery(DeliveryDto deliveryDto) {
        Delivery delivery = Delivery.builder()
                .id(deliveryDto.getId())
                .deliveryStart(deliveryDto.getDeliveryStart())
                .deliveryEnd(deliveryDto.getDeliveryEnd())
                .capacity(deliveryDto.getCapacity())
                .employee(deliveryDto.getEmployee())
                .build();

        return delivery;
    }

    public DeliveryDto deliveryToDeliveryDto(Delivery delivery) {
        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setId(delivery.getId());
        deliveryDto.setDeliveryStart(delivery.getDeliveryStart());
        deliveryDto.setDeliveryEnd(delivery.getDeliveryEnd());
        deliveryDto.setCapacity(delivery.getCapacity());
        deliveryDto.setEmployee(delivery.getEmployee());

        return deliveryDto;
    }
}
