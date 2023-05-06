package ztw.nextapp.web.mapper;

import org.springframework.stereotype.Component;
import ztw.nextapp.domain.Person;
import ztw.nextapp.web.model.PersonDto;

@Component
public class PersonMapper {
    public Person personDtoToPerson(PersonDto personDto) {
        return Person.builder()
                .id(personDto.getId())
                .name(personDto.getName())
                .telephone(personDto.getTelephone())
                .driverLicense(personDto.getDriverLicense())
                .build();
    }

    public PersonDto personToPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setName(person.getName());
        personDto.setTelephone(person.getTelephone());
        personDto.setDriverLicense(person.getDriverLicense());
        return personDto;
    }
}
