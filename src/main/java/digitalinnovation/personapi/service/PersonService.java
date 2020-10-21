package digitalinnovation.personapi.service;

import digitalinnovation.personapi.dto.response.MessageResponserDTO;
import digitalinnovation.personapi.entity.Person;
import digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    public MessageResponserDTO createPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return MessageResponserDTO
                .builder()
                .message("Created person with ID" + savedPerson.getId())
                .build();

    }
}

