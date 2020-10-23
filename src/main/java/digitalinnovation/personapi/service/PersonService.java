package digitalinnovation.personapi.service;

import digitalinnovation.personapi.dto.request.PersonDTO;
import digitalinnovation.personapi.dto.response.MessageResponserDTO;
import digitalinnovation.personapi.entity.Person;
import digitalinnovation.personapi.exception.PersonNotFoundException;
import digitalinnovation.personapi.mapper.PersonMapper;
import digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
         Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }


    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);

        personRepository.deleteById(id);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
}

