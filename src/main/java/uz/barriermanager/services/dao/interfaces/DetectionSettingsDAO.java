package uz.barriermanager.services.dao.interfaces;

import uz.barriermanager.models.DetectionSettings;

/**
 * Detection Settings' DAO.
 *
 * @author Alisher Kasimov
 * @version 0.1.0012
 */
public interface DetectionSettingsDAO {
    DetectionSettings getSettings();
    DetectionSettings saveSettings(DetectionSettings settings);
    DetectionSettings editSettings(DetectionSettings settings);
}
