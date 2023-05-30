package ztw.nextapp.services;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.*;
import org.springframework.stereotype.Service;
import ztw.nextapp.domain.DeliveryPoint;
import ztw.nextapp.domain.Route;
import ztw.nextapp.domain.RoutePoint;
import ztw.nextapp.exceptions.IllegalOperationException;
import ztw.nextapp.repositories.DeliveryPointRepository;
import ztw.nextapp.repositories.RoutePointRepository;
import ztw.nextapp.repositories.RouteRepository;
import ztw.nextapp.web.model.DeliveryPointDto;
import ztw.nextapp.web.model.VehicleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private GeoApiContext geoApiContext;
    private RouteRepository routeRepository;
    private RoutePointRepository routePointRepository;
    private DeliveryPointRepository deliveryPointRepository;

    public RouteServiceImpl(GeoApiContext geoApiContext, RouteRepository routeRepository, RoutePointRepository routePointRepository, DeliveryPointRepository deliveryPointRepository) {
        this.geoApiContext = geoApiContext;
        this.routeRepository = routeRepository;
        this.routePointRepository = routePointRepository;
        this.deliveryPointRepository = deliveryPointRepository;
    }

    @Override
    public Set<Route> findAll() {
        return routeRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Route findById(Long aLong) {
        Optional<Route> route= routeRepository.findById(aLong);

        if (route.isPresent()) {
            return route.get();
        } else {
            return null;
        }
    }

    @Override
    public Route save(Route object) {
        return routeRepository.save(object);
    }

    @Override
    public void delete(Route object) {
        routePointRepository.deleteByRouteId(object.getId());
        routeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) throws IllegalOperationException {
        routePointRepository.deleteByRouteId(aLong);
        routeRepository.deleteById(aLong);
    }

    @Override
    public void updateRoute(long id, Route route) {
        routeRepository.update(id, route.getOrigin(), route.getDestination());
    }

    @Override
    public void addRoutePoint(long routeId, Long pointId) {
        routePointRepository.save(routeId, pointId);
    }

    @Override
    public void addRoutePoint(long routeId, String pointName) throws IllegalOperationException {
        Optional<DeliveryPoint> pointOptional = deliveryPointRepository.findByName(pointName);

        if (pointOptional.isPresent()) {
            DeliveryPoint point = pointOptional.get();
            routePointRepository.save(routeId, point.getId());
        } else {
            throw new IllegalOperationException();
        }
    }

    @Override
    public void deleteRoutePoint(Long routeId, Long pointId) {
        routePointRepository.deleteByRouteIdAndPointId(routeId, pointId);
    }

    @Override
    public List<RoutePoint> getRoutePoints(Long routeId) {
        return routePointRepository.findByRouteId(routeId);
    }

    @Override
    public Route getNewRoute() {
        return routeRepository.save(new Route());
    }

    @Override
    public DirectionsResult getDirectionsResult(Long routeId) {
        DirectionsApiRequest directionsApiRequest = DirectionsApi.newRequest(geoApiContext);
        Optional<Route> routeOptional = routeRepository.findById(routeId);

        if (routeOptional.isPresent()) {
            Route route = routeOptional.get();
            List<RoutePoint> routePoints = routePointRepository.findByRouteId(routeId);
            List<DeliveryPoint> deliveryPoints = routePoints
                    .stream()
                    .map(RoutePoint::getDeliveryPoint)
                    .collect(Collectors.toList());

            directionsApiRequest.origin(route.getOrigin());
            directionsApiRequest.destination(route.getDestination());
            directionsApiRequest.mode(TravelMode.DRIVING);
            directionsApiRequest.optimizeWaypoints(true);
            directionsApiRequest.waypoints(deliveryPoints.stream().map(DeliveryPoint::getName).toArray(String[]::new));

            try {
                DirectionsResult result = directionsApiRequest.await();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public DirectionsResult getDirectionsResultUnsaved(String origin, String destination, List<DeliveryPointDto> points) {
        DirectionsApiRequest directionsApiRequest = DirectionsApi.newRequest(geoApiContext);;

        directionsApiRequest.origin(origin);
        directionsApiRequest.destination(destination);
        directionsApiRequest.mode(TravelMode.DRIVING);
        directionsApiRequest.optimizeWaypoints(true);
        directionsApiRequest.waypoints(points.stream().map(DeliveryPointDto::getName).toArray(String[]::new));

        try {
            DirectionsResult result = directionsApiRequest.await();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public DirectionsResult getDirectionsResult(String origin, String destination, ArrayList<String> waypoints) {
//        DirectionsApiRequest directionsApiRequest = DirectionsApi.newRequest(geoApiContext);
//        directionsApiRequest.origin(origin);
//        directionsApiRequest.destination(destination);
//        directionsApiRequest.mode(TravelMode.DRIVING);
//        directionsApiRequest.optimizeWaypoints(true);
//        directionsApiRequest.waypoints(waypoints.toArray(new String[waypoints.size()]));
//
//        try {
//            DirectionsResult result = directionsApiRequest.await();
//            return result.routes[0];
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

//    @Override
//    public DirectionsRoute createRoute(String capacity, String driverId, String origin, String destination, ArrayList<String> waypoints) {
//        DirectionsRoute directionsRoute = getDirections(origin, destination, waypoints);
//        ArrayList<String> resultRoute = new ArrayList<>();
//        Duration duration = new Duration();
//        resultRoute.add(origin);
//
//        for (int i = 0; i < directionsRoute.legs.length; i++) {
//            resultRoute.add(directionsRoute.legs[i].endAddress);
//            duration.inSeconds += directionsRoute.legs[i].duration.inSeconds;
//        }
//
//        System.out.println("Seconds: " + duration.inSeconds);
//        System.out.println("Minutes: " + duration.inSeconds / 60);
//        System.out.println("Hours: " + duration.inSeconds / 3600);
//        System.out.println("Summary: " + directionsRoute.summary);
//        System.out.println("Route: " + directionsRoute);
//        System.out.println("Route: " + resultRoute);
//
//        return directionsRoute;
//    }

}
