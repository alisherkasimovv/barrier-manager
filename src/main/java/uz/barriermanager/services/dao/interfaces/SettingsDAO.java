package uz.barriermanager.services.dao.interfaces;

import uz.barriermanager.models.Settings;

/**
 * Detection Settings' DAO.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
//@Component("detect-settings-dao")
public interface SettingsDAO {
    void saveSettings(Settings settings);
    Settings getSettings();
    double getPrice();
    void editSettings(Settings settings);
}
