package uz.barriermanager.controllers;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.barriermanager.models.Car;
import uz.barriermanager.models.Settings;
import uz.barriermanager.modules.PlateDetectorModule;
import uz.barriermanager.modules.VideoStreamerModule;
import uz.barriermanager.services.dao.interfaces.CameraDAO;
import uz.barriermanager.services.dao.interfaces.CarDAO;
import uz.barriermanager.services.dao.interfaces.SettingsDAO;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

/**
 * Detection controller.
 *
 * @author Alisher Kasimov
 * @version 0.1.0047
 */
@Controller
@RequestMapping("/recognition")
public class DetectionController {
    @Autowired
    private SettingsDAO settingsDAO;

    @Autowired
    private CameraDAO cameraDAO;

    @Autowired
    private CarDAO carDAO;

    private Settings settings;

    ArrayList<String> frames;
    private static VideoStreamerModule streamModule;

    @RequestMapping(value = {"/", "/index"}, name = "recognition-index")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("detection/index");

        model.addObject("contentHeader", "Detected cars");
        model.addObject("contentDescription", "List of detected cars");
        return model;
    }

    @RequestMapping(value = {"/get-detected"})
    public ResponseEntity<List<Car>> getDetected() {
        List<Car> cars = carDAO.getAll();
        for (Car item : cars) {

        }
        return new ResponseEntity<>(carDAO.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/start-streaming")
    public ResponseEntity<String> actionStreaming() {
        streamModule = new VideoStreamerModule(cameraDAO.getOne(0).getUrl(), settingsDAO.getSettings());
        streamModule.startStreaming();

        return new ResponseEntity<>("Streaming started", HttpStatus.OK);
    }

    @PostMapping(value = "/json")
    public ResponseEntity<String> detectFromString(@RequestBody String results) {
        JsonObject jsonObject = new JsonParser().parse(results).getAsJsonObject().getAsJsonObject("best_plate");

        if (jsonObject != null) {
            Car car = new Car();
            car.setPlate(jsonObject.getAsJsonObject().get("plate").toString().replace("\"", ""));
            car.setConfidence(jsonObject.getAsJsonObject().get("confidence").getAsDouble());
            car.setPicture(jsonObject.getAsJsonObject().get("plate_crop_jpeg").toString().replace("\"", ""));

            System.out.println(" -- -- -- Saved car -- -- -- ");
            System.out.println(car.getPlate());
            System.out.println(car.getConfidence());

            try {
                carDAO.checkAndSaveCar(car);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Listening...");
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping(value = "/detect-car")
    public ResponseEntity<List<Car>> actionCapture() {
        streamModule.startDetecting();

        frames = streamModule.getFrames();

        settings = settingsDAO.getSettings();
        PlateDetectorModule module = new PlateDetectorModule(settings);

        for (String frame : frames) {
            if (frame != null) {
                System.out.println(frame);
                Car car = module.startDetecting(frame);

                System.out.println(car.getPlate());
                try {
                    carDAO.checkAndSaveCar(car);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }

        module.stopAlpr();

        return new ResponseEntity<>(carDAO.getAll(), HttpStatus.OK);
    }
}
