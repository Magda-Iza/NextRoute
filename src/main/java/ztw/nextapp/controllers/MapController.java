package ztw.nextapp.controllers;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ztw.nextapp.services.RouteService;

import java.util.ArrayList;

@RestController
public class MapController {
    private static final String API_KEY = "AIzaSyAHy9ZHFybi2s9KD46oJqQ0_ZVhoSmsexQ";
    private GeoApiContext geoApiContext;
    private RouteService routeService;

    public MapController(GeoApiContext geoApiContext, RouteService routeService) {
        this.geoApiContext = geoApiContext;
        this.routeService = routeService;
    }

    @GetMapping("/testApi")
    public String testApi() {
        String origin = "Bezpieczna, Wrocław, Polska";
        String destination = "wybrzeże Stanisława Wyspiańskiego, Wrocław, Polska";
        ArrayList<String> waypoints = new ArrayList<>();
        waypoints.add("Kościuszki, Wrocław, Polska");
        waypoints.add("Kleczkowska, Wrocław, Polska");
        waypoints.add("plac Grunwaldzki, Wrocław, Polska");
        routeService.createRoute("test", "test", origin, destination, waypoints);
        return "Microservice MapController is working!";
    }

    @GetMapping("/map")
    public String map() {
        return "Microservice MapController is working!";
    }

//    @GetMapping("/getGeocoding")
//    public Response getGeocoding(@RequestParam String address) {
//        UriComponents uri = UriComponentsBuilder.newInstance()
//                .scheme("https")
//                .host("maps.googleapis.com")
//                .path("/maps/api/geocode/json")
//                .queryParam("key", API_KEY)
//                .queryParam("address", address)
//                .build();
//
//        ResponseEntity<Response> response = new RestTemplate().getForEntity(uri.toUriString(), Response.class);
//        System.out.println(response.getBody());
//        return response.getBody();
//    }

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

    @GetMapping("/getDirections")
    public DirectionsRoute newDirections() {
//        @RequestParam String origin, @RequestParam String destination,
//        @RequestParam ArrayList<String> waypoints
        String origin = "Bezpieczna, Wrocław, Polska";
        String destination = "wybrzeże Stanisława Wyspiańskiego, Wrocław, Polska";
        ArrayList<String> waypoints = new ArrayList<>();
        waypoints.add("Kościuszki, Wrocław, Polska");
        waypoints.add("Kleczkowska, Wrocław, Polska");
        waypoints.add("plac Grunwaldzki, Wrocław, Polska");

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
            return result.routes[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
