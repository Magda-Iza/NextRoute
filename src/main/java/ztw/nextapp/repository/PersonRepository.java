package ztw.nextapp.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ztw.nextapp.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE employees SET employees.role = ?1 WHERE employees.id = ?2")
    void setRole(String role, Long employeeId);
}
