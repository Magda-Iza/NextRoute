package ztw.nextapp.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ztw.nextapp.domain.Vehicle;

import java.util.List;
import java.util.Optional;


@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM vehicles")
    List<Vehicle> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM vehicles WHERE id = ?1")
    Optional<Vehicle> findById(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO vehicles (id, vin, brand, model, capacity, register_date) VALUES (?1, ?2, ?3, ?4, ?5, ?6)")
    void save(Long id, String vin, String brand, String model, Double capacity, String registerDate);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM vehicles WHERE id = ?1")
    void delete(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE vehicles SET vin = ?2, brand = ?3, model = ?4, capacity = ?5, register_date = ?6 WHERE id = ?1")
    void update(Long id, String vin, String brand, String model, Double capacity, String registerDate);

}
