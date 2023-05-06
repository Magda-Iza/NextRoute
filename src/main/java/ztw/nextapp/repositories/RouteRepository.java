package ztw.nextapp.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ztw.nextapp.domain.Route;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {
        @Query(nativeQuery = true, value = "SELECT * FROM routes")
        List<Route> findAll();

        @Query(nativeQuery = true, value = "SELECT * FROM routes WHERE id = ?1")
        Optional<Route> findById(Long id);

        @Modifying
        @Query(nativeQuery = true, value = "INSERT INTO routes (id) VALUES (?1)")
        void save(Long id);

        @Modifying
        @Query(nativeQuery = true, value = "DELETE FROM routes WHERE id = ?1")
        void delete(Long id);
}
