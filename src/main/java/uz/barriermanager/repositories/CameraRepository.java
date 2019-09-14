package uz.barriermanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.barriermanager.models.Camera;

import java.util.ArrayList;

/**
 * Camera Repository.
 *
 * @author Alisher Kasimov
 * @version 0.1.0046
 */
@Service
@Component("CameraRepository")
public interface CameraRepository extends JpaRepository<Camera, Integer> {
    Camera getById(int id);
    ArrayList<Camera> findAll();
}
