package ztw.nextapp.services;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;
import ztw.nextapp.domain.DeliveryPoint;
import ztw.nextapp.repositories.DeliveryPointRepository;
import ztw.nextapp.web.mapper.DeliveryPointMapper;
import ztw.nextapp.web.model.DeliveryPointDto;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DeliveryPointServiceImpl implements DeliveryPointService {

    private DeliveryPointRepository deliveryPointRepository;
    private DeliveryPointMapper deliveryPointMapper;

    private GeoApiContext geoApiContext;

    public DeliveryPointServiceImpl(DeliveryPointRepository deliveryPointRepository, DeliveryPointMapper deliveryPointMapper, GeoApiContext geoApiContext) {
        this.deliveryPointRepository = deliveryPointRepository;
        this.deliveryPointMapper = deliveryPointMapper;
        this.geoApiContext = geoApiContext;
    }

    @Override
    public LatLng getGeocoding(String address) {
        GeocodingApiRequest geocodingApiRequest = new GeocodingApiRequest(geoApiContext);
        geocodingApiRequest.address(address);

        try {
            GeocodingResult[] geocodingResults = geocodingApiRequest.await();
            System.out.println(geocodingResults[0].geometry.location);
            return geocodingResults[0].geometry.location;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<DeliveryPoint> findAll() {
        return new HashSet<>(deliveryPointRepository.findAll());
    }

    @Override
    public DeliveryPoint findById(Long aLong) {
        return deliveryPointRepository.findById(aLong).get();
    }

    @Override
    public DeliveryPoint save(DeliveryPoint object) {
        return deliveryPointRepository.save(object);
    }

    @Override
    public void delete(DeliveryPoint object) {
        deliveryPointRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        deliveryPointRepository.deleteById(aLong);
    }

    @Override
    public void updateDeliveryPoint(long id, DeliveryPointDto pointDto) {
        LatLng latLng = getGeocoding(pointDto.getName());
        deliveryPointRepository.update(id, pointDto.getName(), String.valueOf(latLng.lat), String.valueOf(latLng.lng));
    }

    @Override
    public DeliveryPoint createDeliveryPoint(DeliveryPointDto pointDto) {
        Optional<DeliveryPoint> pointOptional = deliveryPointRepository.findByName(pointDto.getName());

        if (pointOptional.isPresent()) {
            return pointOptional.get();
        }

        LatLng latLng = getGeocoding(pointDto.getName());
        DeliveryPoint point = DeliveryPoint.builder()
                .name(pointDto.getName())
                .latitude(String.valueOf(latLng.lat))
                .longitude(String.valueOf(latLng.lng))
                .build();

        return deliveryPointRepository.save(point);
    }

    @Override
    public DeliveryPointDto findPointById(long id) {
        return deliveryPointMapper.deliveryPointToDeliveryPointDto(deliveryPointRepository.findById(id).get());
    }

    @Override
    public List<DeliveryPointDto> getAllDeliveryPoints() {
        return deliveryPointRepository.findAll()
                .stream()
                .map(deliveryPointMapper::deliveryPointToDeliveryPointDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryPointDto findPointByName(String name) {
        Optional<DeliveryPoint> point = deliveryPointRepository.findByName(name);

        if (point.isPresent()) {
            return deliveryPointMapper.deliveryPointToDeliveryPointDto(point.get());
        } else {
            return null;
        }
    }
}
