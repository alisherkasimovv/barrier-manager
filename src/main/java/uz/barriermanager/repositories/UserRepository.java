package uz.barriermanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.barriermanager.models.User;

import java.util.List;

/**
 * User repository.
 *
 * @author Alisher Kasimov
 * @version 0.1.0012
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByDeletedIsFalse();
    List<User> findAllByDeletedIsTrue();
    User getById(int id);
}
