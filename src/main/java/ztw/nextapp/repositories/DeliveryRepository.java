package ztw.nextapp.repositories;

import com.google.maps.model.DirectionsResult;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ztw.nextapp.domain.Delivery;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM deliveries")
    List<Delivery> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM deliveries WHERE id = ?1")
    Optional<Delivery> findById(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO deliveries (id, delivery_start_date, delivery_end_date, capacity, employee_id, driver_id, route_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)")
    Delivery save(Long id, LocalDate delivery_start_date, LocalDate delivery_end_date, Double capacity, Long employee_id, Long driver_id, Long route_id);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM deliveries WHERE id = ?1")
    void delete(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE deliveries SET delivery_start_date = ?2, delivery_end_date = ?3, capacity = ?4, employee_id = ?5, driver_id = ?6, route_id = ?7 WHERE id = ?1")
    void update(Long id, LocalDate delivery_start_date, LocalDate delivery_end_date, Double capacity, Long employee_id, Long driver_id, Long route_id);

    @Query(nativeQuery = true, value = "SELECT * FROM deliveries WHERE employee_id = ?1")
    List<Delivery> findByEmployeeId(Long employee_id);

    @Query(nativeQuery = true, value = "SELECT * FROM deliveries WHERE driver_id = ?1")
    List<Delivery> findByDriverId(Long driver_id);

    @Query(nativeQuery = true, value = "SELECT * FROM deliveries WHERE route_id = ?1")
    List<Delivery> findByRouteId(Long route_id);

    @Query(nativeQuery = true, value = "SELECT * FROM deliveries WHERE route_id = ?1 AND driver_id = ?2")
    Optional<Delivery> findByRouteIdDriverId(Long route_id, Long driver_id);

}
