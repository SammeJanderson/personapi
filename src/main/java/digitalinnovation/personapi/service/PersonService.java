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
        return CreateMessageResponse(savedPerson.getId(), "Created person with ID");
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


    public MessageResponserDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);

        Person uptadedPerson = personRepository.save(personToUpdate);
        return CreateMessageResponse(uptadedPerson.getId(), "Updated person with ID");

    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponserDTO CreateMessageResponse(Long id, String message) {
        return MessageResponserDTO
                .builder()
                .message(message + id)
                .build();
    }
}

