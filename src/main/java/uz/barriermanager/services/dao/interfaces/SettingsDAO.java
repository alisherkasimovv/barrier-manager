package uz.barriermanager.services.dao.interfaces;

import uz.barriermanager.models.Settings;

/**
 * Detection Settings' DAO.
 *
 * @author Alisher Kasimov
 * @version 0.1.0046
 */
//@Component("detect-settings-dao")
public interface SettingsDAO {
    Settings getSettings();
    void editSettings(Settings settings);
}
