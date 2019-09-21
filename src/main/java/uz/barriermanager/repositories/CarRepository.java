package uz.barriermanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.barriermanager.models.Car;

import java.util.List;

/**
 * Car repository.
 *
 * @author Alisher Kasimov
 * @version 0.1.0047
 */
@Service
@Component("CarRepository")
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car getById(int id);
    List<Car> findAllByPlate(String plate);
    Car findCarByPlate(String plate);
    Car getCarByPlateAndDeparturedIsFalse(String plate);
}
