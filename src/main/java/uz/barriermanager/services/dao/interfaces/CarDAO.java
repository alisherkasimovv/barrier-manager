package uz.barriermanager.services.dao.interfaces;

import uz.barriermanager.models.Car;

import java.util.List;

/**
 * Car DAO.
 *
 * @author Alisher Kasimov
 * @version 0.1.0012
 */
public interface CarDAO {
    List<Car> getAll();
    List<Car> getDeleted();
    Car getOneCar(Integer id);
    Car saveCar(Car car);
    Car editCar(Car car);
    void deleteCar(Integer id);
}
