package uz.barriermanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.barriermanager.models.DetectionSettings;

/**
 * Detection Settings repository.
 *
 * @author Alisher Kasimov
 * @version 0.1.0035
 */
public interface DetectionSettingsRepository extends JpaRepository<DetectionSettings, Integer> {
    DetectionSettings findById(int id);
}
