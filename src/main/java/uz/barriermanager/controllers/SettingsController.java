package uz.barriermanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.barriermanager.models.Settings;
import uz.barriermanager.services.dao.interfaces.SettingsDAO;

import javax.validation.Valid;

/**
 * Settings controller class.
 *
 * @author Alisher Kasimov
 * @version 0.1.0047
 */
@Controller
@RequestMapping("/global")
public class SettingsController {
    @Autowired
    private SettingsDAO settingsDAO;

    @GetMapping(value = "/get-all")
    public ResponseEntity<Settings> openDetectionSettings() {
        return new ResponseEntity<>(settingsDAO.getSettings(), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Settings> saveDetectionSettings(@Valid @RequestBody Settings settings) {
        settingsDAO.editSettings(settings);
        return new ResponseEntity<>(settingsDAO.getSettings(), HttpStatus.OK);
    }
}
