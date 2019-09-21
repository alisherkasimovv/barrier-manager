package uz.barriermanager.services.dao.interfaces;

import uz.barriermanager.models.User;

import java.util.List;

/**
 * User DAO.
 *
 * @author Alisher Kasimov
 * @version 0.1.0047
 */
public interface UserDAO {
    List<User> getAll();
    List<User> getDeleted();
    User getOneUser(Integer id);
    User saveUser(User user);
    User editUser(User user);
    void deleteUser(Integer id);
}
