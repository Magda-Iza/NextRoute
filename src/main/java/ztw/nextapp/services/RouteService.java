package ztw.nextapp.services;

import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import ztw.nextapp.domain.DeliveryPoint;
import ztw.nextapp.domain.Route;
import ztw.nextapp.domain.RoutePoint;
import ztw.nextapp.domain.Vehicle;
import ztw.nextapp.exceptions.IllegalOperationException;
import ztw.nextapp.web.model.DeliveryPointDto;
import ztw.nextapp.web.model.VehicleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface RouteService extends BaseService<Route, Long> {
    DirectionsResult getDirectionsResult(Long routeId);

    DirectionsResult getDirectionsResultUnsaved(String origin, String destination, List<DeliveryPointDto> points);

    Set<Route> findAll();
    Route findById(Long id);

    void updateRoute(long id, Route route);

    void addRoutePoint(long routeId, Long pointId);

    void addRoutePoint(long routeId, String pointName) throws IllegalOperationException;

    void deleteRoutePoint(Long routeId, Long pointId);

    List<RoutePoint> getRoutePoints(Long routeId);

    Route getNewRoute();
}
