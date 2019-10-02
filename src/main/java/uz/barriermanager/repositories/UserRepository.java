package uz.barriermanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import uz.barriermanager.models.User;

import java.util.List;

/**
 * User repository.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
@Component("user-repository")
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByDeletedIsFalse();
    List<User> findAllByDeletedIsTrue();
    User getById(int id);
    User findByUsername(String s);
    int countAllByDeletedFalse();
}
