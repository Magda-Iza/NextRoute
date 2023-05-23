package ztw.nextapp.services;

import ztw.nextapp.domain.DeliveryVehicle;
import ztw.nextapp.domain.Route;

import java.util.List;

public interface DeliveryVehicleService extends BaseService<DeliveryVehicle, Long> {
    public void deleteByDeliveryId(Long deliveryId);

    public List<DeliveryVehicle> findByDeliveryId(Long deliveryId);
}
