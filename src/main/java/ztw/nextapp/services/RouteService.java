package ztw.nextapp.services;

import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import ztw.nextapp.domain.Route;

import java.util.ArrayList;

public interface RouteService extends BaseService<Route, Long> {
    DirectionsRoute getDirections(String origin, String destination, ArrayList<String> waypoints);
    LatLng getGeocoding(String address);
    DirectionsRoute createRoute(String type, String capacity,
                     String origin, String destination, ArrayList<String> waypoints);
}
