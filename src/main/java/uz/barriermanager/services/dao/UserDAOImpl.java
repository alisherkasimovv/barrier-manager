package uz.barriermanager.services.dao;

import org.springframework.beans.factory.annotation.Autowired;
import uz.barriermanager.models.User;
import uz.barriermanager.repositories.UserRepository;
import uz.barriermanager.services.dao.interfaces.UserDAO;

import java.util.List;

/**
 * User DAO implementation.
 *
 * @author Alisher Kasimov
 * @version 0.1.0047
 */
public class UserDAOImpl implements UserDAO {
    @Autowired
    UserRepository repository;

    @Override
    public List<User> getAll() {
        return repository.findAllByDeletedIsFalse();
    }

    @Override
    public List<User> getDeleted() {
        return repository.findAllByDeletedIsTrue();
    }

    @Override
    public User getOneUser(Integer id) {
        return repository.getById(id);
    }

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public User editUser(User user) {
        User temp = repository.getById(user.getId());
        temp.setUsername(user.getUsername());
        temp.setPassword(user.getUsername());
        return repository.save(temp);
    }

    @Override
    public void deleteUser(Integer id) {
        User temp = repository.getById(id);
        temp.setDeleted(true);
        repository.save(temp);
    }
}
