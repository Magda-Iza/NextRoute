package ztw.nextapp.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ztw.nextapp.domain.Delivery;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

//@Builder
//public Delivery(Long id, LocalDate delivery_start, LocalDate delivery_end) {
//        super(id);
//        this.delivery_start = delivery_start;
//        this.delivery_end = delivery_end;
//        }
//
//@Column(name = "delivery_start_date")
//private LocalDate delivery_start;
//
//@Column(name = "delivery_end_date")
//private LocalDate delivery_end;
//
//@Column(name = "capacity")
//private Double capacity;
//
//
//@ManyToOne(fetch = FetchType.LAZY)
//@JoinColumn(name = "employee_id")
//private Person employee;
//
//@ManyToOne(fetch = FetchType.LAZY)
//@JoinColumn(name = "driver_id")
//private Person person;
//
//@ManyToOne(fetch = FetchType.LAZY)
//@JoinColumn(name = "route_id")
//private Route route;
//
//@OneToMany(cascade = CascadeType.ALL, mappedBy = "delivery")
//private List<DeliveryVehicle> deliveryVehicles = new ArrayList<>();
@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM deliveries")
    List<Delivery> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM deliveries WHERE id = ?1")
    Optional<Delivery> findById(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO deliveries (delivery_start_date, delivery_end_date, capacity, employee_id, driver_id, route_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6)")
    void save(String delivery_start_date, String delivery_end_date, Double capacity, Long employee_id, Long driver_id, Long route_id);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM deliveries WHERE id = ?1")
    void delete(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE deliveries SET delivery_start_date = ?2, delivery_end_date = ?3, capacity = ?4, employee_id = ?5, driver_id = ?6, route_id = ?7 WHERE id = ?1")
    void update(Long id, String delivery_start_date, String delivery_end_date, Double capacity, Long employee_id, Long driver_id, Long route_id);

    @Query(nativeQuery = true, value = "SELECT * FROM deliveries WHERE employee_id = ?1")
    List<Delivery> findByEmployeeId(Long employee_id);

    @Query(nativeQuery = true, value = "SELECT * FROM deliveries WHERE driver_id = ?1")
    List<Delivery> findByDriverId(Long driver_id);

    @Query(nativeQuery = true, value = "SELECT * FROM deliveries WHERE route_id = ?1")
    List<Delivery> findByRouteId(Long route_id);

}
