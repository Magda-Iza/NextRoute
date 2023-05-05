package ztw.nextapp.controllers;

import org.springframework.stereotype.Controller;
import ztw.nextapp.services.PersonService;

@Controller
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
}
