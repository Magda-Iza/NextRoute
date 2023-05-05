package ztw.nextapp.web.mapper;

import org.springframework.stereotype.Component;
import ztw.nextapp.domain.Delivery;
import ztw.nextapp.web.model.DeliveryDto;

@Component
public class DeliveryMapper {

    public Delivery deliveryDtoToDelivery(DeliveryDto deliveryDo) {
        return Delivery.builder().build();///
    }

    public DeliveryDto deliveryToDeliveryDto(Delivery delivery) {
        DeliveryDto deliveryDto = new DeliveryDto();
        ///
        return deliveryDto;
    }
}
