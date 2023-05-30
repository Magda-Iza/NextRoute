package ztw.nextapp.web.mapper;

import org.springframework.stereotype.Component;
import ztw.nextapp.domain.DeliveryPoint;
import ztw.nextapp.web.model.DeliveryPointDto;

@Component
public class DeliveryPointMapper {

    public DeliveryPointDto deliveryPointToDeliveryPointDto(DeliveryPoint deliveryPoint) {
        DeliveryPointDto deliveryPointDto = new DeliveryPointDto();
        deliveryPointDto.setId(deliveryPoint.getId());
        deliveryPointDto.setName(deliveryPoint.getName());

        return deliveryPointDto;
    }

    public DeliveryPoint deliveryPointDtoToDeliveryPoint(DeliveryPointDto deliveryPointDto) {
        DeliveryPoint deliveryPoint = new DeliveryPoint();
        deliveryPoint.setId(deliveryPointDto.getId());
        deliveryPoint.setName(deliveryPointDto.getName());

        return deliveryPoint;
    }
}

