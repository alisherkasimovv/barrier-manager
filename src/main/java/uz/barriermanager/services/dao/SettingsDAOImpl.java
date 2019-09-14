package uz.barriermanager.services.dao;

import org.springframework.beans.factory.annotation.Autowired;
import uz.barriermanager.models.Settings;
import uz.barriermanager.repositories.SettingsRepository;
import uz.barriermanager.services.dao.interfaces.SettingsDAO;

/**
 * Implementation of SettingsDAO interface.
 *
 * @author Alisher Kasimov
 * @version 0.1.0046
 */
public class SettingsDAOImpl implements SettingsDAO {
    @Autowired
    SettingsRepository repository;

    @Override
    public Settings getSettings() {
        return repository.findById(1);
    }

    @Override
    public void editSettings(Settings settings) {
        Settings temp = repository.findById(1);

        temp.setCountry(settings.getCountry());
        temp.setConfigs(settings.getConfigs());
        temp.setRuntime(settings.getRuntime());
        temp.setStreamShotCount(settings.getStreamShotCount());
        temp.setStreamShotIntervalMs(settings.getStreamShotIntervalMs());
        temp.setPrice(settings.getPrice());

        repository.save(temp);
    }
}
