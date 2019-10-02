package uz.barriermanager.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uz.barriermanager.models.Settings;
import uz.barriermanager.services.dao.CameraDAOImpl;
import uz.barriermanager.services.dao.CarDAOImpl;
import uz.barriermanager.services.dao.SettingsDAOImpl;
import uz.barriermanager.services.dao.UserDAOImpl;
import uz.barriermanager.services.dao.interfaces.CameraDAO;
import uz.barriermanager.services.dao.interfaces.CarDAO;
import uz.barriermanager.services.dao.interfaces.SettingsDAO;
import uz.barriermanager.services.dao.interfaces.UserDAO;

/**
 * Application config class.
 * Defining beans.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
@Configuration
public class ApplicationConfig {
    @Bean
    public CarDAO CarDAO() {
        return new CarDAOImpl();
    }

    @Bean
    public Settings Settings() {
        return new Settings();
    }

    @Bean
    public UserDAO UserDAO() {
        return new UserDAOImpl();
    }

    @Bean
    public CameraDAO CameraDAO() {
        return new CameraDAOImpl();
    }

    @Bean
    public SettingsDAO detectionSettingsDAO() {
        return new SettingsDAOImpl();
    }
}
