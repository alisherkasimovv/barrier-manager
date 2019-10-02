package uz.barriermanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.barriermanager.models.Car;

import java.util.List;

/**
 * Car repository.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
@Service
@Component("CarRepository")
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car getById(int id);
    Car findFirstByPlateNumberOrderByIdDesc(String id);
    Car getByLastDepartureTrue();
    Car getByLastDepartureTrueAndGoneTrue();
}
