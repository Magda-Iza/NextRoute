package ztw.nextapp.services;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.*;
import org.springframework.stereotype.Service;
import ztw.nextapp.domain.Route;

import java.util.ArrayList;
import java.util.Set;

@Service
public class RouteServiceImpl implements RouteService {
    private GeoApiContext geoApiContext;

    public RouteServiceImpl(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }

    @Override
    public Set<Route> findAll() {
        return null;
    }

    @Override
    public Route findById(Long aLong) {
        return null;
    }

    @Override
    public Route save(Route object) {
        return null;
    }

    @Override
    public void delete(Route object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public DirectionsRoute getDirections(String origin, String destination, ArrayList<String> waypoints) {
        DirectionsApiRequest directionsApiRequest = DirectionsApi.newRequest(geoApiContext);
        directionsApiRequest.origin(origin);
        directionsApiRequest.destination(destination);
        directionsApiRequest.mode(TravelMode.DRIVING);
        directionsApiRequest.optimizeWaypoints(true);
        directionsApiRequest.waypoints(waypoints.toArray(new String[waypoints.size()]));

        try {
            DirectionsResult result = directionsApiRequest.await();
            return result.routes[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LatLng getGeocoding(String address) {
        GeocodingApiRequest geocodingApiRequest = new GeocodingApiRequest(geoApiContext);
        geocodingApiRequest.address(address);

        try {
            return geocodingApiRequest.await()[0].geometry.location;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DirectionsRoute createRoute(String capacity, String driverId, String origin, String destination, ArrayList<String> waypoints) {
        DirectionsRoute directionsRoute = getDirections(origin, destination, waypoints);
        ArrayList<String> resultRoute = new ArrayList<>();
        Duration duration = new Duration();
        resultRoute.add(origin);

        for (int i = 0; i < directionsRoute.legs.length; i++) {
            resultRoute.add(directionsRoute.legs[i].endAddress);
            duration.inSeconds += directionsRoute.legs[i].duration.inSeconds;
        }

        System.out.println("Seconds: " + duration.inSeconds);
        System.out.println("Minutes: " + duration.inSeconds / 60);
        System.out.println("Hours: " + duration.inSeconds / 3600);
        System.out.println("Summary: " + directionsRoute.summary);
        System.out.println("Route: " + directionsRoute);
        System.out.println("Route: " + resultRoute);

        return directionsRoute;
    }
}
