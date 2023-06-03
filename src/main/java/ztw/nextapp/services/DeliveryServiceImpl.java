package ztw.nextapp.services;

import com.google.maps.model.DirectionsResult;
import org.springframework.stereotype.Service;
import ztw.nextapp.domain.*;
import ztw.nextapp.exceptions.IllegalOperationException;
import ztw.nextapp.repositories.DeliveryPointRepository;
import ztw.nextapp.repositories.DeliveryRepository;
import ztw.nextapp.repositories.RouteRepository;
import ztw.nextapp.web.mapper.DeliveryMapper;
import ztw.nextapp.web.mapper.DeliveryPointMapper;
import ztw.nextapp.web.mapper.VehicleMapper;
import ztw.nextapp.web.model.DeliveryDto;
import ztw.nextapp.web.model.DeliveryPointDto;
import ztw.nextapp.web.model.VehicleDto;

import javax.transaction.Transactional;
import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final RouteService routeService;
    private final VehicleService vehicleService;
    private final DeliveryVehicleService deliveryVehicleService;
    private final VehicleMapper vehicleMapper;
    private final DeliveryPointMapper deliveryPointMapper;
    private final RouteRepository routeRepository;
    private final DeliveryPointRepository deliveryPointRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper,
                               RouteService routeService, VehicleService vehicleService,
                               DeliveryVehicleService deliveryVehicleService, VehicleMapper vehicleMapper,
                               DeliveryPointMapper deliveryPointMapper, RouteRepository routeRepository,
                               DeliveryPointRepository deliveryPointRepository) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryMapper = deliveryMapper;
        this.routeService = routeService;
        this.vehicleService = vehicleService;
        this.deliveryVehicleService = deliveryVehicleService;
        this.vehicleMapper = vehicleMapper;
        this.deliveryPointMapper = deliveryPointMapper;
        this.routeRepository = routeRepository;
        this.deliveryPointRepository = deliveryPointRepository;
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

    @Transactional
    @Override
    public Delivery save(Delivery object) {
        return deliveryRepository.save(object);
    }

    @Transactional
    @Override
    public void delete(Delivery object) {
        deliveryRepository.delete(object);
        deliveryVehicleService.deleteByDeliveryId(object.getId());
    }

    @Transactional
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

    @Transactional
    @Override
    public void updateDelivery(long id, DeliveryDto deliveryDto) {
        Optional<Delivery> deliveryOptional = deliveryRepository.findById(id);

        if (deliveryOptional.isPresent()) {
            Delivery delivery = deliveryOptional.get();
            deliveryRepository.update(id, deliveryDto.getDeliveryStart(), deliveryDto.getDeliveryEnd(), deliveryDto.getCapacity(), delivery.getEmployee().getId(), delivery.getPerson().getId(), delivery.getRoute().getId());
        }
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

//    @Override
//    public Delivery createDelivery(DeliveryDto deliveryDto) {
//        Delivery delivery = deliveryMapper.deliveryDtoToDelivery(deliveryDto);
//        Delivery savedDelivery = deliveryRepository.save(delivery);
//        assignVehiclesToDelivery(savedDelivery.getId());
//
//        return savedDelivery;
//    }

    private String removeSpecialCharacters(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String result = pattern.matcher(normalized).replaceAll("");
        return result.replaceAll("ł", "l");
    }

    private boolean validatePoint(String address) {
        Optional<DeliveryPoint> pointOptional = deliveryPointRepository.findByName(address);
        Optional<DeliveryPoint> pointOptionalWithoutChars = deliveryPointRepository
                .findByName(removeSpecialCharacters(address));

        if (!pointOptional.isPresent() && !pointOptionalWithoutChars.isPresent()) {
            return false;
        }

        return true;
    }

    @Transactional
    @Override
    public Delivery createDelivery(DeliveryDto deliveryDto) throws IllegalOperationException {
        System.out.println("weszlo");

        if (!validatePoint(deliveryDto.getOrigin()) || !validatePoint(deliveryDto.getDestination())) {
            throw new IllegalOperationException();
        }

        for (DeliveryPointDto pointDto : deliveryDto.getPoints()) {
            if (!validatePoint(pointDto.getName())) {
                throw new IllegalOperationException();
            }
        }

        Route route = new Route();
        System.out.println(deliveryDto.getOrigin());
        System.out.println(deliveryDto.getDestination());
        System.out.println(deliveryDto.getPoints());
        System.out.println(deliveryDto.getCapacity());

        route.setOrigin(deliveryDto.getOrigin());
        route.setDestination(deliveryDto.getDestination());
        route = routeRepository.save(route);

        for (DeliveryPointDto pointDto : deliveryDto.getPoints()) {
            routeService.addRoutePoint(route.getId(), pointDto.getName());
        }

        Delivery delivery = Delivery.builder()
                .deliveryStart(deliveryDto.getDeliveryStart())
                .deliveryEnd(deliveryDto.getDeliveryEnd())
                .capacity(deliveryDto.getCapacity())
                .employee(null)
                .person(null)
                .route(route)
                .build();

        System.out.println("wyszlo");
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

    @Override
    public List<DeliveryPointDto> getDeliveryRoutePoints(Long deliveryId) {
        Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);

        if(delivery.isPresent()) {
            List<RoutePoint> routePoints = routeService.getRoutePoints(delivery.get().getRoute().getId());

            return routePoints.stream()
                    .map(routePoint -> deliveryPointMapper
                            .deliveryPointToDeliveryPointDto(routePoint.getDeliveryPoint()))
                    .collect(Collectors.toList());
        }
        else
            return null;
    }

    @Override
    public DeliveryDto getNewDelivery() {
        Delivery delivery = deliveryRepository.save(Delivery.builder().build());
        System.out.println("deliveryserviceimpl samo " + delivery.getId());
        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setId(delivery.getId());
        System.out.println("deliveryserviceimpl po maperze" + deliveryDto.getId());

        return deliveryDto;
    }
}
