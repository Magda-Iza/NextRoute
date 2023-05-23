package ztw.nextapp.services;

import com.google.maps.model.DirectionsResult;
import ztw.nextapp.domain.Delivery;
import ztw.nextapp.domain.Person;
import ztw.nextapp.web.model.DeliveryDto;

import java.util.List;


public interface DeliveryService extends BaseService<Delivery, Long> {

    void updateDelivery(long id, DeliveryDto deliveryDto);

    Delivery createDelivery(DeliveryDto deliveryDto);

    List<DeliveryDto> getDeliveries();

    List<DeliveryDto> getDriverDeliveries(Long i);

    DeliveryDto getDeliverByRouteIdDriverId(Long routeId, Long driverId);

    DeliveryDto findDeliveryById(long id);

    DirectionsResult getDirectionsResult(Long deliveryId);
}
