package uz.barriermanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.barriermanager.models.DetectionSettings;
import uz.barriermanager.services.dao.interfaces.DetectionSettingsDAO;

import javax.validation.Valid;

/**
 * Settings controller class.
 *
 * @author Alisher Kasimov
 * @version 0.1.0035
 */
@Controller
@RequestMapping("/settings")
public class SettingsController {
    @Autowired
    private DetectionSettingsDAO detectionSettingsDAO;

    @GetMapping(value = "/detection/get-all")
    public ResponseEntity<DetectionSettings> openDetectionSettings() {
        return new ResponseEntity<>(detectionSettingsDAO.getSettings(), HttpStatus.OK);
    }

    @PostMapping(value = "/detection/save")
    public ResponseEntity<DetectionSettings> saveDetectionSettings(@Valid @RequestBody DetectionSettings detect) {
        detectionSettingsDAO.editSettings(detect);
        return new ResponseEntity<>(detectionSettingsDAO.getSettings(), HttpStatus.OK);
    }
}
