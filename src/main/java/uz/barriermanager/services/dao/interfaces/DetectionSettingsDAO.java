package uz.barriermanager.services.dao.interfaces;

import org.springframework.stereotype.Component;
import uz.barriermanager.models.DetectionSettings;

/**
 * Detection Settings' DAO.
 *
 * @author Alisher Kasimov
 * @version 0.1.0035
 */
//@Component("detect-settings-dao")
public interface DetectionSettingsDAO {
    DetectionSettings getSettings();
    DetectionSettings saveSettings(DetectionSettings settings);
    void editSettings(DetectionSettings settings);
}
