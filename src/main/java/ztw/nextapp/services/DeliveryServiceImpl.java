package ztw.nextapp.services;

import com.google.maps.model.DirectionsResult;
import org.springframework.stereotype.Service;
import ztw.nextapp.domain.Delivery;
import ztw.nextapp.domain.DeliveryVehicle;
import ztw.nextapp.exceptions.IllegalOperationException;
import ztw.nextapp.repositories.DeliveryRepository;
import ztw.nextapp.web.mapper.DeliveryMapper;
import ztw.nextapp.web.mapper.VehicleMapper;
import ztw.nextapp.web.model.DeliveryDto;
import ztw.nextapp.web.model.VehicleDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final RouteService routeService;
    private final VehicleService vehicleService;
    private final DeliveryVehicleService deliveryVehicleService;
    private final VehicleMapper vehicleMapper;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper, RouteService routeService, VehicleService vehicleService, DeliveryVehicleService deliveryVehicleService, VehicleMapper vehicleMapper) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryMapper = deliveryMapper;
        this.routeService = routeService;
        this.vehicleService = vehicleService;
        this.deliveryVehicleService = deliveryVehicleService;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public Set<Delivery> findAll() {
        return deliveryRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Delivery findById(Long aLong) {
        Optional<Delivery> delivery = deliveryRepository.findById(aLong);

        if (delivery.isPresent()) {
            return delivery.get();
        } else {
            return null;
        }
    }

    @Override
    public Delivery save(Delivery object) {
        return deliveryRepository.save(object);
    }

    @Override
    public void delete(Delivery object) {
        deliveryRepository.delete(object);
        deliveryVehicleService.deleteByDeliveryId(object.getId());
    }

    @Override
    public void deleteById(Long aLong) throws IllegalOperationException {
        Optional<Delivery> delivery = deliveryRepository.findById(aLong);
        if (delivery.isPresent()) {
            deliveryRepository.deleteById(aLong);
            deliveryVehicleService.deleteByDeliveryId(aLong);
        }
        else
            throw new IllegalOperationException();
    }

    @Override
    public void updateDelivery(long id, DeliveryDto deliveryDto) {
        deliveryRepository.update(id, deliveryDto.getDeliveryStart(), deliveryDto.getDeliveryEnd(), deliveryDto.getCapacity(), deliveryDto.getEmployee().getId(), deliveryDto.getPerson().getId(), deliveryDto.getRouteId());
    }

    private void assignVehiclesToDelivery(Long deliveryId) {
        Optional<Delivery> deliveryOptional = deliveryRepository.findById(deliveryId);

        if (deliveryOptional.isPresent()) {
            Delivery delivery = deliveryOptional.get();
            List<VehicleDto> vehicles = vehicleService.findVehiclesForRoute(delivery.getCapacity());

            for (VehicleDto vehicle : vehicles) {
                deliveryVehicleService.save(new DeliveryVehicle(delivery, vehicleMapper.vehicleDtoToVehicle(vehicle)));
            }
        }
    }

    @Override
    public Delivery createDelivery(DeliveryDto deliveryDto) {
        Delivery delivery = deliveryMapper.deliveryDtoToDelivery(deliveryDto);
        Delivery savedDelivery = deliveryRepository.save(delivery);
        assignVehiclesToDelivery(savedDelivery.getId());

        return savedDelivery;
    }

    @Override
    public List<DeliveryDto> getDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        List<DeliveryDto> deliveriesDto = deliveries.stream()
                .map(deliveryMapper::deliveryToDeliveryDto)
                .collect(Collectors.toList());

        return deliveriesDto;
    }

    @Override
    public List<DeliveryDto> getDriverDeliveries(Long i) {
        List<Delivery> deliveries = deliveryRepository.findByDriverId(i);

        return deliveries.stream()
                .map(deliveryMapper::deliveryToDeliveryDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryDto getDeliverByRouteIdDriverId(Long routeId, Long driverId) {
        Optional<Delivery> delivery =  deliveryRepository.findByRouteIdDriverId(routeId, driverId);

        if(delivery.isPresent())
            return deliveryMapper.deliveryToDeliveryDto(delivery.get());
        else
            return null;
    }

    @Override
    public DeliveryDto findDeliveryById(long id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);

        if(delivery.isPresent()) {
            return deliveryMapper.deliveryToDeliveryDto(delivery.get());
        }
        else
            return null;
    }

    @Override
    public DirectionsResult getDirectionsResult(Long deliveryId) {
        return routeService.getDirectionsResult(deliveryId);
    }
}
