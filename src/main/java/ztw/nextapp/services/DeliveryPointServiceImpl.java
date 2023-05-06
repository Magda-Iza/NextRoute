package ztw.nextapp.services;

import org.springframework.stereotype.Service;
import ztw.nextapp.domain.DeliveryPoint;
import ztw.nextapp.web.model.DeliveryPointDto;

import java.util.List;
import java.util.Set;

@Service
public class DeliveryPointServiceImpl implements DeliveryPointService{
    @Override
    public Set<DeliveryPoint> findAll() {
        return null;
    }

    @Override
    public DeliveryPoint findById(Long aLong) {
        return null;
    }

    @Override
    public DeliveryPoint save(DeliveryPoint object) {
        return null;
    }

    @Override
    public void delete(DeliveryPoint object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void updateDeliveryPoint(long id, DeliveryPointDto pointDto) {
        
    }

    @Override
    public DeliveryPoint createDeliveryPoint(DeliveryPointDto pointDto) {
        return null;
    }

    @Override
    public DeliveryPointDto findPointById(long id) {
        return null;
    }

    @Override
    public List<DeliveryPointDto> getPoints() {
        return null;
    }
}
