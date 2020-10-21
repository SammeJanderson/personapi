package digitalinnovation.personapi.service;

import digitalinnovation.personapi.dto.request.PersonDTO;
import digitalinnovation.personapi.dto.response.MessageResponserDTO;
import digitalinnovation.personapi.entity.Person;
import digitalinnovation.personapi.mapper.PersonMapper;
import digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    public MessageResponserDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponserDTO
                .builder()
                .message("Created person with ID" + savedPerson.getId())
                .build();

    }
}

