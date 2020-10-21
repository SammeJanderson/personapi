package digitalinnovation.personapi.controller;


import digitalinnovation.personapi.dto.request.PersonDTO;
import digitalinnovation.personapi.dto.response.MessageResponserDTO;
import digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponserDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
    return personService.createPerson(personDTO);
    }
}
