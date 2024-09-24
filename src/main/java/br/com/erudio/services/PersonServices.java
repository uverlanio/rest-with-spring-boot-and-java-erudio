package br.com.erudio.services;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findById(Long id) {
        Optional<Person> person = repository.findById(id);
        return person.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public void create(Person person) {
        repository.save(person);
    }

    public Person update(Person person) {
        Optional<Person> personToSave = repository.findById(person.getId());

        if (personToSave.isPresent()) {
            personToSave.get().setFirstName(person.getFirstName());
            personToSave.get().setLastName(person.getLastName());
            personToSave.get().setAddress(person.getAddress());
            personToSave.get().setGender(person.getGender());

            repository.save(personToSave.get());
        }
        return personToSave.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public void delete(Long id) {
        var personFounded = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));;
        repository.delete(personFounded);
    }

}
