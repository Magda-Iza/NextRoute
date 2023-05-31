package ztw.nextapp.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ztw.nextapp.domain.DeliveryVehicle;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryVehicleRepository extends CrudRepository<DeliveryVehicle, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM delivery_vehicle")
    List<DeliveryVehicle> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM delivery_vehicle WHERE id = ?1")
    Optional<DeliveryVehicle> findById(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO delivery_vehicle (delivery_id, vehicle_id, driver_id) VALUES (?1, ?2, ?3)")
    void save(Long delivery_id, Long vehicle_id, Long driver_id);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM delivery_vehicle WHERE id = ?1")
    void delete(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM delivery_vehicle WHERE delivery_id = ?1")
    void deleteByDeliveryId(Long deliveryId);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE delivery_vehicle SET delivery_id = ?2, vehicle_id = ?3, driver_id = ?4 WHERE id = ?1")
    void update(Long id, Long delivery_id, Long vehicle_id, Long driver_id);

    @Query(nativeQuery = true, value = "SELECT * FROM delivery_vehicle WHERE delivery_id = ?1")
    List<DeliveryVehicle> findByDeliveryId(Long delivery_id);

    @Query(nativeQuery = true, value = "SELECT * FROM delivery_vehicle WHERE vehicle_id = ?1")
    List<DeliveryVehicle> findByVehicleId(Long vehicle_id);

    @Query(nativeQuery = true, value = "SELECT * FROM delivery_vehicle WHERE driver_id = ?1")
    List<DeliveryVehicle> findByDriverId(Long driver_id);
}
