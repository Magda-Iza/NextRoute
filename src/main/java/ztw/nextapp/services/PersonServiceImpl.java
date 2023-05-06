package ztw.nextapp.services;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import ztw.nextapp.domain.Person;
import ztw.nextapp.repositories.PersonRepository;

import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Set<Person> findAll() {
        return null;
    }

    @Override
    public Person findById(Long aLong) {
        return null;
    }

    @Override
    public Person save(Person object) {
        return null;
    }

    @Override
    public void delete(Person object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    @Modifying
    public void setUserRole(Person person, String role) {
        Long employeeId = person.getId();
        personRepository.setRole(role, employeeId);
    }
}
