package uz.barriermanager.services.dao.interfaces;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.barriermanager.models.Car;

import java.util.List;

/**
 * Car DAO.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
public interface CarDAO {
    List<Car> getAll();
    Car getCar(String plateNumber);
    void saveArrival(Car car);
    Car saveDeparture(Car car);
    Car getLastDeparture();

    Car editCar(Car car);
}
