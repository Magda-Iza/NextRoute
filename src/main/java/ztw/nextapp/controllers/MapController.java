package ztw.nextapp.controllers;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ztw.nextapp.domain.Delivery;
import ztw.nextapp.domain.Route;
import ztw.nextapp.domain.Vehicle;
import ztw.nextapp.services.DeliveryService;
import ztw.nextapp.services.RouteService;
import ztw.nextapp.web.model.DeliveryDto;
import ztw.nextapp.web.model.VehicleDto;

import java.util.ArrayList;

@RestController
public class MapController {
    private static final String API_KEY = "AIzaSyAHy9ZHFybi2s9KD46oJqQ0_ZVhoSmsexQ";
    private final GeoApiContext geoApiContext;
    private final RouteService routeService;
    private final DeliveryService deliveryService;;

    public MapController(GeoApiContext geoApiContext, RouteService routeService, DeliveryService deliveryService) {
        this.geoApiContext = geoApiContext;
        this.routeService = routeService;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/testApi")
    public String testApi() {
        String origin = "Beżowa 5, Wrocław, Polska";
        String destination = "Zabraniecka 20, Warszawa, Polska";
        ArrayList<String> waypoints = new ArrayList<>();
        waypoints.add("Kościuszki, Wrocław, Polska");
        waypoints.add("Kleczkowska, Wrocław, Polska");
        waypoints.add("plac Grunwaldzki, Wrocław, Polska");
        return "Microservice MapController is working!";
    }

    @GetMapping("/map")
    public String map() {
        return "Microservice MapController is working!";
    }

    @GetMapping("/getGeocoding")
    public LatLng getGeocoding() {
        String address = "Bezpieczna, Wrocław, Polska";
        GeocodingApiRequest geocodingApiRequest = new GeocodingApiRequest(geoApiContext);
        geocodingApiRequest.address(address);

        try {
            return geocodingApiRequest.await()[0].geometry.location;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/directions/map")
    public ResponseEntity<DirectionsResult> createMap(@RequestBody DeliveryDto delivery) {
        try {
            System.out.println(delivery.getOrigin());
            System.out.println(delivery.getDestination());
            System.out.println(delivery.getPoints());
            DirectionsResult directionsResult = routeService.getDirectionsResultUnsaved(delivery.getOrigin(), delivery.getDestination(), delivery.getPoints());
            return new ResponseEntity<>(directionsResult, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDirections")
    public ResponseEntity<DirectionsResult> newDirections() {
        String origin = "Bezpieczna, Wrocław, Polska";
        String destination = "wybrzeże Stanisława Wyspiańskiego, Wrocław, Polska";
        ArrayList<String> waypoints = new ArrayList<>();
        waypoints.add("Kościuszki, Wrocław, Polska");
        waypoints.add("Kleczkowska, Wrocław, Polska");
        waypoints.add("plac Grunwaldzki, Wrocław, Polska");
        waypoints.add("Szkolna 20, Wisznia Mała, Polska");
        waypoints.add("Warszawa, Polska");

        DirectionsApiRequest directionsApiRequest = DirectionsApi.newRequest(geoApiContext);
        directionsApiRequest.origin(origin);
        directionsApiRequest.destination(destination);
        directionsApiRequest.mode(TravelMode.DRIVING);
        directionsApiRequest.optimizeWaypoints(true);
        directionsApiRequest.waypoints(waypoints.toArray(new String[waypoints.size()]));

        try {
            DirectionsResult result = directionsApiRequest.await();
            // kolejnosc odwiedzonych punktow
            // System.out.println(Arrays.toString(result.routes[0].waypointOrder));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/delivery/route/{id}")
    public ResponseEntity<DirectionsResult> getDeliveryDirectionsResult(@PathVariable String id) {
        try {
            Long deliveryIdLong = Long.parseLong(id);
            Delivery delivery = deliveryService.findById(deliveryIdLong);
            DirectionsResult result = routeService.getDirectionsResult(delivery.getRoute().getId());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/route/{id}")
    public ResponseEntity<DirectionsResult> getRouteDirectionsResult(@PathVariable String id) {
        try {
            Route route = routeService.findById(Long.parseLong(id));
            DirectionsResult result = routeService.getDirectionsResult(route.getId());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
