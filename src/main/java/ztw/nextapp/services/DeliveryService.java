package ztw.nextapp.services;

import com.google.maps.model.DirectionsResult;
import ztw.nextapp.domain.Delivery;
import ztw.nextapp.domain.Person;
import ztw.nextapp.exceptions.IllegalOperationException;
import ztw.nextapp.web.model.DeliveryDto;
import ztw.nextapp.web.model.DeliveryPointDto;

import java.util.List;


public interface DeliveryService extends BaseService<Delivery, Long> {

    void updateDelivery(long id, DeliveryDto deliveryDto);

    Delivery createDelivery(DeliveryDto deliveryDto) throws IllegalOperationException;

    List<DeliveryDto> getDeliveries();

    List<DeliveryDto> getDriverDeliveries(String name);

    List<DeliveryDto> getEmployeeDeliveries(String name);

    DeliveryDto getDeliverByRouteIdDriverId(Long routeId, Long driverId);

    DeliveryDto findDeliveryById(long id);

    DirectionsResult getDirectionsResult(Long deliveryId);

    List<DeliveryPointDto> getDeliveryRoutePoints(Long deliveryId);

    DeliveryDto getNewDelivery();

    DeliveryDto assignEmployeeToDelivery(Long deliveryId, String name);
}
