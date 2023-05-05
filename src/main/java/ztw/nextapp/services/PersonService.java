package ztw.nextapp.services;

import ztw.nextapp.domain.Person;

public interface PersonService extends BaseService<Person, Long> {

    void setUserRole(Person person, String role);
}
