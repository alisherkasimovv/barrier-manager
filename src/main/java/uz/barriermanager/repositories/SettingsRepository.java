package uz.barriermanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.barriermanager.models.Settings;

/**
 * Detection Settings repository.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
public interface SettingsRepository extends JpaRepository<Settings, Integer> {
    Settings findById(int id);
}
