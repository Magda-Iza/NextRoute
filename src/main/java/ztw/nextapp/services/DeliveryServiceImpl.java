package ztw.nextapp.services;

import org.springframework.stereotype.Service;
import ztw.nextapp.domain.Delivery;
import ztw.nextapp.exceptions.IllegalOperationException;
import ztw.nextapp.repositories.DeliveryRepository;
import ztw.nextapp.web.model.DeliveryDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Set<Delivery> findAll() {
        return null;
    }

    @Override
    public Delivery findById(Long aLong) {
        return null;
    }

    @Override
    public Delivery save(Delivery object) {
        return null;
    }

    @Override
    public void delete(Delivery object) {

    }

    @Override
    public void deleteById(Long aLong) throws IllegalOperationException {
        Optional<Delivery> delivery = deliveryRepository.findById(aLong);
        if(delivery.isPresent() != false)
            deliveryRepository.deleteById(aLong);
        else
            throw new IllegalOperationException();
    }

    @Override
    public void updateDelivery(long id, DeliveryDto deliveryDto) {

    }

    @Override
    public Delivery createDelivery(DeliveryDto deliveryDto) {
        return null;
    }

    @Override
    public List<DeliveryDto> getDeliveries() {
        return null;
    }

    @Override
    public List<DeliveryDto> getDriverDeliveries(Long i) {
        return null;
    }

    @Override
    public DeliveryDto findDeliveryById(long id) {
        return null;
    }
}
