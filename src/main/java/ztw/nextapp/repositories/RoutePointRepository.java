package ztw.nextapp.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ztw.nextapp.domain.RoutePoint;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoutePointRepository extends CrudRepository<RoutePoint, Long> {

        @Query(nativeQuery = true, value = "SELECT * FROM route_points")
        List<RoutePoint> findAll();

        @Query(nativeQuery = true, value = "SELECT * FROM route_points WHERE id = ?1")
        Optional<RoutePoint> findById(Long id);

        @Modifying
        @Query(nativeQuery = true, value = "INSERT INTO route_points (route_id, point_id) VALUES (?1, ?2)")
        void save(Long routeId, Long pointId);

        @Modifying
        @Query(nativeQuery = true, value = "DELETE FROM route_points WHERE id = ?1")
        void delete(Long id);

        @Modifying
        @Query(nativeQuery = true, value = "DELETE FROM route_points WHERE route_id = ?1")
        void deleteByRouteId(Long routeId);

        @Modifying
        @Query(nativeQuery = true, value = "DELETE FROM route_points WHERE route_id = ?1 AND point_id = ?2")
        void deleteByRouteIdAndPointId(Long routeId, Long pointId);

        @Modifying
        @Query(nativeQuery = true, value = "UPDATE route_points SET route_id = ?2, point_id = ?3 WHERE id = ?1")
        void update(Long id, Long routeId, Long pointId);

        @Query(nativeQuery = true, value = "SELECT * FROM route_points WHERE route_id = ?1")
        List<RoutePoint> findByRouteId(Long routeId);
}
