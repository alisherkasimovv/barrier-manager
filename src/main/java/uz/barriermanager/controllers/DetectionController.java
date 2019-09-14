package uz.barriermanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.barriermanager.models.Settings;
import uz.barriermanager.modules.PlateDetectorModule;
import uz.barriermanager.modules.VideoStreamerModule;
import uz.barriermanager.services.dao.interfaces.SettingsDAO;

/**
 * Car DAO.
 *
 * @author Alisher Kasimov
 * @version 0.1.0046
 */
@Controller
@RequestMapping("/recognition")
public class DetectionController {
    @Autowired
    private SettingsDAO settingsDAO;

    private Settings settings;

    private VideoStreamerModule streamModule;
    private PlateDetectorModule detectorModule;

    @RequestMapping(value = {"/", "/index"}, name = "recognition-index")
    public String index() {

        settings = new Settings();
        settings.setCountry(settingsDAO.getSettings().getCountry());
        settings.setConfigs(settingsDAO.getSettings().getConfigs());
        settings.setRuntime(settingsDAO.getSettings().getRuntime());
        settings.setStreamShotCount(settingsDAO.getSettings().getStreamShotCount());
        //ModelAndView model = new ModelAndView("recognition/index");
        streamModule = new VideoStreamerModule("rtsp://192.168.0.50:554/profile3", settings);
        streamModule.startStreaming();
        streamModule.startDetecting();

        return "index";
    }
}
