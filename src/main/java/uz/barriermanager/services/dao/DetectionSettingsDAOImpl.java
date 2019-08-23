package uz.barriermanager.services.dao;

import org.springframework.beans.factory.annotation.Autowired;
import uz.barriermanager.models.DetectionSettings;
import uz.barriermanager.repositories.DetectionSettingsRepository;
import uz.barriermanager.services.dao.interfaces.DetectionSettingsDAO;

/**
 * Implementation of DetectionSettingsDAO interface.
 *
 * @author Alisher Kasimov
 * @version 0.1.0035
 */
public class DetectionSettingsDAOImpl implements DetectionSettingsDAO {
    @Autowired
    DetectionSettingsRepository repository;

    @Override
    public DetectionSettings getSettings() {
        return repository.findById(1);
    }

    @Override
    public DetectionSettings saveSettings(DetectionSettings settings) {
        return repository.save(settings);
    }

    @Override
    public void editSettings(DetectionSettings settings) {
        DetectionSettings temp = repository.findById(1);
        temp.setCountry(settings.getCountry());
        temp.setConfigs(settings.getConfigs());
        temp.setRuntime(settings.getRuntime());
        repository.save(temp);
    }
}
