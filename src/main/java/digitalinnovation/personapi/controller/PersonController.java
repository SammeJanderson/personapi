package digitalinnovation.personapi.controller;


import digitalinnovation.personapi.dto.request.PersonDTO;
import digitalinnovation.personapi.dto.response.MessageResponserDTO;
import digitalinnovation.personapi.exception.PersonNotFoundException;
import digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findByID(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

}
