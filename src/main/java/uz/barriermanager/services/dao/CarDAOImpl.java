package uz.barriermanager.services.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.barriermanager.models.Car;
import uz.barriermanager.repositories.CarRepository;
import uz.barriermanager.services.dao.interfaces.CarDAO;
import uz.barriermanager.services.dao.interfaces.SettingsDAO;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Implementation of CarDAO interface.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
public class CarDAOImpl implements CarDAO {
    @Autowired
    @Qualifier(value = "CarRepository")
    private CarRepository repository;

//    @Autowired
//    private SettingsDAO settingsDAO;

    @Override
    public List<Car> getAll() {
        return repository.findAll();
    }

    public Car getCar(String plateNumber) {
        return repository.findFirstByPlateNumberOrderByIdDesc(plateNumber);
    }

    /**
     * Saving new arrived car with current timestamp
     *
     * @param car Newly created instance with plate, confidence and picture
     */
    public void saveArrival(Car car) {
        car.setDateArrival(LocalDateTime.now().toString());
        car.setGone(false);
        car.setLastDeparture(false);
        repository.save(car);
    }

    /**
     * Updating already arrived car by registering it's departure time
     * and difference between arrival and departure.
     *
     * @param car Detected car which's data will be saved like departure time.
     */
    public Car saveDeparture(Car car) {
        Car previous = repository.getByLastDepartureTrueAndGoneTrue();
        if (previous != null) {
            previous.setLastDeparture(false);
            repository.save(previous);
        }

        car.setDateDeparture(LocalDateTime.now().toString());
        car.setGone(true);
        car.setTimeSpent(
                ChronoUnit.HOURS.between(
                        LocalDateTime.parse(car.getDateArrival()),
                        LocalDateTime.parse(car.getDateDeparture()))
        );

        car.setCost((car.getTimeSpent() / 60) * 2000);// (long) settingsDAO.getPrice());
        car.setLastDeparture(true);
        return repository.save(car);
    }

    @Override
    public Car getLastDeparture() {
        return repository.getByLastDepartureTrue();
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

        return repository.save(temp);
    }
}
