package ztw.nextapp.services;

import org.springframework.stereotype.Service;
import ztw.nextapp.domain.DeliveryPoint;
import ztw.nextapp.repositories.DeliveryPointRepository;
import ztw.nextapp.web.mapper.DeliveryPointMapper;
import ztw.nextapp.web.model.DeliveryPointDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DeliveryPointServiceImpl implements DeliveryPointService {

    DeliveryPointRepository deliveryPointRepository;
    DeliveryPointMapper deliveryPointMapper;

    public DeliveryPointServiceImpl(DeliveryPointRepository deliveryPointRepository, DeliveryPointMapper deliveryPointMapper) {
        this.deliveryPointRepository = deliveryPointRepository;
        this.deliveryPointMapper = deliveryPointMapper;
    }

    @Override
    public Set<DeliveryPoint> findAll() {
        return deliveryPointRepository.findAll().stream().collect(Collectors.toSet());
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
        deliveryPointRepository.update(id, pointDto.getName(), pointDto.getLatitude(), pointDto.getLongitude());
    }

    @Override
    public DeliveryPoint createDeliveryPoint(DeliveryPointDto pointDto) {
        return deliveryPointRepository.save(deliveryPointMapper.deliveryPointDtoToDeliveryPoint(pointDto));
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
