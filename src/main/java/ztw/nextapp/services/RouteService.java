package ztw.nextapp.services;

import com.google.maps.model.DirectionsRoute;
import ztw.nextapp.domain.Route;

import java.util.ArrayList;

public interface RouteService extends BaseService<Route, Long> {
    DirectionsRoute getDirections(String origin, String destination, ArrayList<String> waypoints);

}
