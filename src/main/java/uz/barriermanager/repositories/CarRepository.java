package uz.barriermanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.barriermanager.models.Car;

import java.util.List;

/**
 * Car repository.
 *
 * @author Alisher Kasimov
 * @version 0.1.0012
 */
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByDeletedIsFalse();
    List<Car> findAllByDeletedIsTrue();
    Car getById(int id);
}
