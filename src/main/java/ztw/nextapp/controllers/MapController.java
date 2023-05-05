package ztw.nextapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ztw.nextapp.web.model.geocoding.Response;

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

    @GetMapping("/getDirections")
    public Response getDirections(@RequestParam String origin, @RequestParam String destination) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("maps.googleapis.com")
                .path("/maps/api/directions/v2:computeRoutes/json")
                .queryParam("key", API_KEY)
                .queryParam("origin", origin)
                .queryParam("destination", destination)
                .queryParam("travelMode", "DRIVE")
                .build();

        ResponseEntity<Response> response = new RestTemplate().getForEntity(uri.toUriString(), Response.class);
        System.out.println(response.getBody());
        return response.getBody();
    }

//    @GetMapping("/getPlacePicker")
//    public String getPlacePicker() {
//        return "placeAutocomplete";
//    }

}
