package ztw.nextapp.web.mapper;

import org.springframework.stereotype.Component;
import ztw.nextapp.domain.Delivery;
import ztw.nextapp.domain.Person;
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
        Person employee = personMapper.personDtoToPerson(deliveryDto.getEmployee());
        Person person = personMapper.personDtoToPerson(deliveryDto.getPerson());

        Delivery delivery = Delivery.builder()
                .id(deliveryDto.getId())
                .deliveryStart(deliveryDto.getDeliveryStart())
                .deliveryEnd(deliveryDto.getDeliveryEnd())
                .capacity(deliveryDto.getCapacity())
                .person(person)
                .employee(employee)
                .build();
        return delivery;
    }

    public DeliveryDto deliveryToDeliveryDto(Delivery delivery) {
        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setId(delivery.getId());
        deliveryDto.setDeliveryStart(delivery.getDeliveryStart());
        deliveryDto.setDeliveryEnd(delivery.getDeliveryEnd());
        deliveryDto.setCapacity(delivery.getCapacity());
        deliveryDto.setEmployee(personMapper.personToPersonDto(delivery.getEmployee()));
        deliveryDto.setPerson(personMapper.personToPersonDto(delivery.getPerson()));
        deliveryDto.setRouteId(delivery.getRoute().getId());
        deliveryDto.setVehicleNumber(delivery.getDeliveryVehicles().size());
        deliveryDto.setRouteMap(delivery.getRouteMap());

        return deliveryDto;
    }
}
