package uz.barriermanager.services.dao.interfaces;

import org.springframework.stereotype.Service;
import uz.barriermanager.models.Car;

import java.util.List;

/**
 * Car DAO.
 *
 * @author Alisher Kasimov
 * @version 0.1.0047
 */
public interface CarDAO {
    List<Car> getAll();
    List<Car> getAllByPlate(String plate);
    void checkAndSaveCar(Car car);
    Car editCar(Car car);
}
