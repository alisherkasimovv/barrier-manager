package uz.barriermanager.services.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uz.barriermanager.models.Car;
import uz.barriermanager.repositories.CarRepository;
import uz.barriermanager.services.dao.interfaces.CarDAO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of CarDAO interface.
 *
 * @author Alisher Kasimov
 * @version 0.1.0047
 */
public class CarDAOImpl implements CarDAO {
    @Autowired
    @Qualifier(value = "CarRepository")
    private CarRepository repository;

    @Override
    public List<Car> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Car> getAllByPlate(String plate) {
        return repository.findAllByPlate(plate);
    }
    /**
     * In default, method is used for saving car according to its type.
     * Checks car from database and saves it.
     *
     * @param car Detected car.
     */
    @Override
    public void checkAndSaveCar(Car car) {
        List<Car> all = getAllByPlate(car.getPlate());

        if (!all.isEmpty()) {
            for (Car item : all) {
                if (item.getDateDeparture() == null) {
                    System.out.println("\nSaving departure of the car");
                    saveDeparture(item);
                } else {
                    System.out.println("\nSaving arrival of the car");
                    saveArrival(car);
                }
            }
        } else {
            saveArrival(car);
        }
    }

    private Car getOneCar(Car car) {
        return repository.findCarByPlate(car.getPlate());
    }

    /**
     * Saving new arrived car with current timestamp
     *
     * @param car Newly created instance with plate, confidence and picture
     */
    private void saveArrival(Car car) {
        car.setDateArrival(LocalTime.now().toString());
        car.setDepartured(false);
        repository.save(car);
    }

    /**
     * Updating already arrived car by registering it's departure time
     * and difference between arrival and departure.
     *
     * @param car Detected car which's data will be saved like departure time.
     */
    private void saveDeparture(Car car) {
        car.setDateDeparture(LocalTime.now().toString());
        car.setDepartured(true);
        car.setTimeSpent(
                ChronoUnit.MINUTES.between(
                        LocalTime.parse(car.getDateArrival()),
                        LocalTime.parse(car.getDateDeparture()))
        );

        repository.save(car);
    }

    /**
     * Ability for editing chosen car
     *
     * @param car Chosen car
     * @return Returning saved car instance for further usage.
     */
    @Override
    public Car editCar(Car car) {
        Car temp = repository.getById(car.getId());

        temp.setPlate(car.getPlate());
        temp.setConfidence(car.getConfidence());
        temp.setRecognizingTime(car.getRecognizingTime());
        temp.setPicture(car.getPicture());
        return repository.save(temp);
    }
}
