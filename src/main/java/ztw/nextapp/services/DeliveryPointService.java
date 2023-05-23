package ztw.nextapp.services;

import ztw.nextapp.domain.DeliveryPoint;
import ztw.nextapp.web.model.DeliveryPointDto;

import java.util.List;

public interface DeliveryPointService extends BaseService<DeliveryPoint, Long> {
    void updateDeliveryPoint(long id, DeliveryPointDto pointDto);

    DeliveryPoint createDeliveryPoint(DeliveryPointDto pointDto);

    DeliveryPointDto findPointById(long id);

    List<DeliveryPointDto> getAllDeliveryPoints();

    DeliveryPointDto findPointByName(String name);
}
