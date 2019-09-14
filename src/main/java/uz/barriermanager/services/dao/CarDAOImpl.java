package uz.barriermanager.services.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import uz.barriermanager.models.Car;
import uz.barriermanager.repositories.CarRepository;
import uz.barriermanager.services.dao.interfaces.CarDAO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Implementation of CarDAO interface.
 *
 * @author Alisher Kasimov
 * @version 0.1.0046
 */
public class CarDAOImpl implements CarDAO {
    @Autowired
    @Qualifier("CarRepository")
    private CarRepository repository;

    @Override
    public List<Car> getAll() {
        return repository.findAllByDeletedIsFalse();
    }

    @Override
    public List<Car> getDeleted() {
        return repository.findAllByDeletedIsTrue();
    }

    @Override
    public Car getOneCar(Integer id) {
        return repository.getById(id);
    }

    @Override
    public Car getOneCar(Car car) {
        return repository.findCarByPlate(car.getPlate());
    }

    @Override
    public void saveArrival(Car car) {
        car.setDateArrival(LocalTime.now().toString());
        saveCar(car);
    }

    @Override
    public void saveDeparture(Car car) {
        Car temp = getOneCar(car);
        temp.setDateDeparture(LocalTime.now().toString());
        temp.setTimeSpent(
                ChronoUnit.MINUTES.between(
                        LocalDateTime.parse(temp.getDateArrival()),
                        LocalDateTime.parse(temp.getDateDeparture()))
        );

        saveCar(temp);
    }

    @Override
    public Car saveCar(Car car) {
        return repository.save(car);
    }

    @Override
    public Car editCar(Car car) {
        Car temp = repository.getById(car.getId());

        temp.setPlate(car.getPlate());
        temp.setConfidence(car.getConfidence());
        temp.setRecognizingTime(car.getRecognizingTime());
        temp.setPicture(car.getPicture());
        return repository.save(temp);
    }

    @Override
    public void deleteCar(Integer id) {
        Car temp = repository.getById(id);
        temp.setDeleted(true);
        repository.save(temp);
    }
}
