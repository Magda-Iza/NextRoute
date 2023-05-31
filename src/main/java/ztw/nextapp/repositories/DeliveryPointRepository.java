package ztw.nextapp.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ztw.nextapp.domain.DeliveryPoint;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryPointRepository extends CrudRepository<DeliveryPoint, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM delivery_points")
    List<DeliveryPoint> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM delivery_points WHERE id = ?1")
    Optional<DeliveryPoint> findById(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO delivery_points (name, latitude, longitude) VALUES (?1, ?2, ?3)")
    void save(String name, String latitude, String longitude);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM delivery_points WHERE id = ?1")
    void delete(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE delivery_points SET name = ?2, latitude = ?3, longitude = ?4 WHERE id = ?1")
    void update(Long id, String name, String latitude, String longitude);

    @Query(nativeQuery = true, value = "SELECT * FROM delivery_points WHERE LOWER(name) LIKE LOWER(?1)")
    Optional<DeliveryPoint> findByName(String name);

}
