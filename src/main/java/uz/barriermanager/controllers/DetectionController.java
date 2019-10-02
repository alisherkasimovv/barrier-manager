package uz.barriermanager.controllers;

import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.barriermanager.OnStartupListener;
import uz.barriermanager.SpringContext;
import uz.barriermanager.models.Camera;
import uz.barriermanager.models.Car;
import uz.barriermanager.modules.VideoStreamerModule;
import uz.barriermanager.services.dao.interfaces.CameraDAO;
import uz.barriermanager.services.dao.interfaces.CarDAO;
import uz.barriermanager.services.dao.interfaces.SettingsDAO;

import java.util.*;

/**
 * Detection controller.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
@Controller
@RequestMapping("/recognition")
public class DetectionController {
    @Autowired private SettingsDAO settingsDAO;
    @Autowired private CameraDAO cameraDAO;
    @Autowired private CarDAO carDAO;

//    public DetectionController() {
//        cameraDAO = SpringContext.getBean(CameraDAO.class);
//        carDAO = SpringContext.getBean(CarDAO.class);
//    }

    private Logger logger = LoggerFactory.getLogger(DetectionController.class);

    // After first call of this object cars List will be filled with data from
    // List populated from database on startup.
    private static Map<String, Camera> cameraMap = null; //OnStartupListener.cameras;
    private static VideoStreamerModule streamModule;
    private static int dbCounter = 0;
    private static JsonObject jsonObject;
    ArrayList<String> frames;

    /**
     * Returning view for index page of detected cars
     *
     * @return ModelAndView
     */
    @RequestMapping(value = {"/", "/index"}, name = "recognition-index")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("detection/index");

        model.addObject("contentHeader", "Detected cars");
        model.addObject("contentDescription", "List of detected cars");
        return model;
    }

    @RequestMapping(value = {"/get-detected"})
    public ResponseEntity<List<Car>> getDetected() {
        return new ResponseEntity<>(carDAO.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/start-streaming")
    public ResponseEntity<String> actionStreaming() {
        streamModule = new VideoStreamerModule(cameraDAO.getOne(0).getUrl(), settingsDAO.getSettings());
        streamModule.startStreaming();

        return new ResponseEntity<>("Streaming started", HttpStatus.OK);
    }



//    @GetMapping(value = "/detect-car")
//    public ResponseEntity<List<Car>> actionCapture() {
//        streamModule.startDetecting();
//
//        frames = streamModule.getFrames();
//
//        settings = settingsDAO.getSettings();
//        PlateDetectorModule module = new PlateDetectorModule(settings);
//
//        for (String frame : frames) {
//            if (frame != null) {
//                System.out.println(frame);
//                Car car = module.startDetecting(frame);
//
//                System.out.println(car.getPlate());
//                try {
//                    carDAO.checkAndSaveCar(car);
//                } catch (NullPointerException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        module.stopAlpr();
//
//        return new ResponseEntity<>(carDAO.getAll(), HttpStatus.OK);
//    }
}
