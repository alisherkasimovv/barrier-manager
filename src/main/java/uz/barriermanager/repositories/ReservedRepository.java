package uz.barriermanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.barriermanager.models.Reserved;

import java.util.List;

/**
 * Reserved car repository.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
@Service
@Component("ReservedRepository")
public interface ReservedRepository extends JpaRepository<Reserved, Integer> {
    List<Reserved> getAll();
    Reserved getById(int id);
}
