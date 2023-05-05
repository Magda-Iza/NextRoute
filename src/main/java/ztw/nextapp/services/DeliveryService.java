package ztw.nextapp.services;

import ztw.nextapp.domain.Delivery;
import ztw.nextapp.domain.Person;
import ztw.nextapp.web.model.DeliveryDto;


public interface DeliveryService extends BaseService<Delivery, Long> {

    void update(Delivery delivery);
}
