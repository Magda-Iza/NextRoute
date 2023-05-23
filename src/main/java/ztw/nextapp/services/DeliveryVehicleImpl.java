package ztw.nextapp.services;

import org.springframework.stereotype.Service;
import ztw.nextapp.domain.DeliveryVehicle;
import ztw.nextapp.exceptions.IllegalOperationException;
import ztw.nextapp.repositories.DeliveryVehicleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DeliveryVehicleImpl implements DeliveryVehicleService {

    private final DeliveryVehicleRepository deliveryVehicleRepository;

    public DeliveryVehicleImpl(DeliveryVehicleRepository deliveryVehicleRepository) {
        this.deliveryVehicleRepository = deliveryVehicleRepository;
    }

    @Override
    public Set<DeliveryVehicle> findAll() {
        return new HashSet<>(deliveryVehicleRepository.findAll());
    }

    @Override
    public DeliveryVehicle findById(Long aLong) {
        Optional<DeliveryVehicle> deliveryVehicle = deliveryVehicleRepository.findById(aLong);

        if (deliveryVehicle.isPresent()) {
            return deliveryVehicle.get();
        } else {
            return null;
        }
    }

    @Override
    public DeliveryVehicle save(DeliveryVehicle object) {
        return deliveryVehicleRepository.save(object);
    }

    @Override
    public void delete(DeliveryVehicle object) {
        deliveryVehicleRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) throws IllegalOperationException {
        deliveryVehicleRepository.deleteById(aLong);
    }

    @Override
    public void deleteByDeliveryId(Long deliveryId) {
        deliveryVehicleRepository.deleteByDeliveryId(deliveryId);
    }

    @Override
    public List<DeliveryVehicle> findByDeliveryId(Long deliveryId) {
        return deliveryVehicleRepository.findByDeliveryId(deliveryId);
    }
}
