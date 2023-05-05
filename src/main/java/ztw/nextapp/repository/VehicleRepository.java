package ztw.nextapp.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ztw.nextapp.domain.Vehicle;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM vehicles")
    List<Vehicle> findAll();

}
