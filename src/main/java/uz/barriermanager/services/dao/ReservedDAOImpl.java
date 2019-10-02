package uz.barriermanager.services.dao;

import org.springframework.beans.factory.annotation.Autowired;
import uz.barriermanager.models.Reserved;
import uz.barriermanager.repositories.ReservedRepository;
import uz.barriermanager.services.dao.interfaces.ReservedDAO;

import java.util.List;

/**
 * Implementation of CarDAO interface.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
public class ReservedDAOImpl implements ReservedDAO {
    @Autowired
    private ReservedRepository repository;

    @Override
    public List<Reserved> getAll() {
        return repository.getAll();
    }

    @Override
    public Reserved getReservedCar(int id) {
        return repository.getById(id);
    }

    @Override
    public Reserved editReservedCar(Reserved reserved) {
        Reserved res = repository.getById(reserved.getId());
        res.setPlateNumber(reserved.getPlateNumber());
        res.setPlateImage(reserved.getPlateImage());
        res.setDescription(reserved.getDescription());
        return repository.save(res);
    }

    @Override
    public Reserved saveReservedCar(Reserved reserved) {
        return repository.save(reserved);
    }

    @Override
    public void deleteReservedCar(Reserved reserved) {
        repository.delete(reserved);
    }
}
