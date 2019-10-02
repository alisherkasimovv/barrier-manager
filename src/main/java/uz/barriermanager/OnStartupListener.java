package uz.barriermanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import uz.barriermanager.models.Settings;
import uz.barriermanager.services.dao.interfaces.SettingsDAO;

public class OnStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SettingsDAO settingsDAO;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        saveInitialSettings();
    }

    /**
     * Try to retrieve settings from database.
     * If database returned null, method populates it with initial data.
     */
    private void saveInitialSettings() {
        Settings settings = new Settings();

        if (settingsDAO.getSettings() == null) {
            settings.setId(1);
            settings.setCountry("eu");
            settings.setConfigs("openalpr.conf");
            settings.setRuntime("runtime_data");
            settings.setStreamShotCount(5);
            settings.setStreamShotIntervalMs(200);
            settings.setPrice(1500.0);

            settingsDAO.saveSettings(settings);
        }
    }
}
