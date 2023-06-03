package ztw.nextapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ztw.nextapp.services.PersonService;

@Controller
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/login")
    public ResponseEntity<HttpStatus> logPerson() {
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
