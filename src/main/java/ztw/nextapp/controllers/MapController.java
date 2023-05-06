package ztw.nextapp.controllers;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ztw.nextapp.web.model.geocoding.Response;

import java.util.ArrayList;

@RestController
public class MapController {
    private static final String API_KEY = "AIzaSyAHy9ZHFybi2s9KD46oJqQ0_ZVhoSmsexQ";

    @GetMapping("/map")
    public String map() {
        return "Microservice MapController is working!";
    }

//    @GetMapping("/getGeocoding")
//    public Response getGeocoding() {
//        String address = "kolkata";
//        ResponseEntity<Response> response = new RestTemplate().getForEntity("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=AIzaSyAHy9ZHFybi2s9KD46oJqQ0_ZVhoSmsexQ", Response.class);
//        return response.getBody();
//    }

    @GetMapping("/getGeocoding")
    public Response getGeocoding(@RequestParam String address) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("maps.googleapis.com")
                .path("/maps/api/geocode/json")
                .queryParam("key", API_KEY)
                .queryParam("address", address)
                .build();

        ResponseEntity<Response> response = new RestTemplate().getForEntity(uri.toUriString(), Response.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

//    @GetMapping("/getDirections")
//    public Response getDirections(@RequestParam String origin, @RequestParam String destination) {
//        UriComponents uri = UriComponentsBuilder.newInstance()
//                .scheme("https")
//                .host("maps.googleapis.com")
//                .path("/maps/api/directions/v2:computeRoutes/json")
//                .queryParam("key", API_KEY)
//                .queryParam("origin", origin)
//                .queryParam("destination", destination)
//                .queryParam("travelMode", "DRIVE")
//                .build();
//
//        ResponseEntity<Response> response = new RestTemplate().getForEntity(uri.toUriString(), Response.class);
//        System.out.println(response.getBody());
//        return response.getBody();
//    }

    // use java client for google maps directions api
//    @GetMapping("/getDirections")
//    public DirectionsApi.Response getDirections() {
////        @RequestParam String origin, @RequestParam ArrayList<String> waypoints
//        String origin = "kolkata";
//        String destination = "delhi";
//        ArrayList<String> waypoints = new ArrayList<>();
//        waypoints.add("mumbai");
//        DirectionsApiRequest directionsApiRequest = DirectionsApi.newRequest(null);
//        directionsApiRequest.origin(origin);
////        directionsApiRequest.destination(destination);
//        directionsApiRequest.mode(TravelMode.valueOf("DRIVING"));
//        directionsApiRequest.optimizeWaypoints(true);
//        directionsApiRequest.waypoints(waypoints.toArray(new String[waypoints.size()]));
//
//        ResponseEntity<DirectionsApi.Response> response = new RestTemplate().getForEntity(directionsApiRequest.toString(), DirectionsApi.Response.class);
//        return response.getBody();
//    }

    @GetMapping("/getDirections")
    public DirectionsRoute getDirections() {
        String origin = "kolkata";
        String destination = "delhi";
        ArrayList<String> waypoints = new ArrayList<>();
        waypoints.add("mumbai");

        // Create a new instance of the Directions API client
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAHy9ZHFybi2s9KD46oJqQ0_ZVhoSmsexQ")
                .build();
        DirectionsApiRequest directionsApiRequest = DirectionsApi.newRequest(context);
        directionsApiRequest.origin(origin);
        directionsApiRequest.destination(destination);
        directionsApiRequest.mode(TravelMode.DRIVING);
        directionsApiRequest.optimizeWaypoints(true);
        directionsApiRequest.waypoints(waypoints.toArray(new String[waypoints.size()]));

        try {
            // Send the API request and get the response
            DirectionsResult result = directionsApiRequest.await();
            return result.routes[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @GetMapping("/getPlacePicker")
//    public String getPlacePicker() {
//        return "placeAutocomplete";
//    }

}
