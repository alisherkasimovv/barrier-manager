package uz.barriermanager.services.dao.interfaces;

import uz.barriermanager.models.Reserved;

import java.util.List;

/**
 * Reserved car DAO.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
public interface ReservedDAO {
    List<Reserved> getAll();
    Reserved getReservedCar(int id);
    Reserved editReservedCar(Reserved reserved);
    Reserved saveReservedCar(Reserved reserved);
    void deleteReservedCar(Reserved reserved);
}
