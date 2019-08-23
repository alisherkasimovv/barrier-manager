package uz.barriermanager.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uz.barriermanager.services.dao.DetectionSettingsDAOImpl;
import uz.barriermanager.services.dao.UserDAOImpl;
import uz.barriermanager.services.dao.interfaces.DetectionSettingsDAO;
import uz.barriermanager.services.dao.interfaces.UserDAO;

/**
 * Application config class.
 * Defining beans.
 *
 * @author Alisher Kasimov
 * @version 0.1.0035
 */
@Configuration
public class ApplicationConfig {
    @Bean
    public DetectionSettingsDAO detectionSettingsDAO() {
        return new DetectionSettingsDAOImpl();
    }

    @Bean
    public UserDAO UserDAO() {
        return new UserDAOImpl();
    }
}
