package ztw.nextapp.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ztw.nextapp.domain.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM employees")
    List<Person> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM employees WHERE id = ?1")
    Optional<Person> findById(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO employees (id, name, telephone, role) VALUES (?1, ?2, ?3, ?4)")
    void save(Long id, String name, String telephone, String userRole);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM employees WHERE id = ?1")
    void delete(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE employees SET name = ?2, telephone = ?3, role = ?4 WHERE id = ?1")
    void update(Long id, String name, String telephone, String userRole);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE employees SET employees.role = ?1 WHERE employees.id = ?2")
    void setRole(String role, Long employeeId);

}
