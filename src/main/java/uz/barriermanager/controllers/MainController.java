package uz.barriermanager.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.barriermanager.models.Car;
import uz.barriermanager.modules.JsonHandler;
import uz.barriermanager.repositories.UserRepository;
import uz.barriermanager.services.dao.interfaces.CameraDAO;
import uz.barriermanager.services.dao.interfaces.CarDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Main controller class.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
@Controller
@Getter
@Setter
public class MainController {
    @Autowired private CarDAO carDAO;
    @Autowired private CameraDAO cameraDAO;
    @Autowired private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(DetectionController.class);
    private static JsonHandler handler = new JsonHandler();
    private static JsonObject alprGroup;

    @RequestMapping(value = {"/", "/index"}, name = "index")
    public ModelAndView index() {
        ModelAndView obj;
        logger.info(cameraDAO.getAll().toString());
        if (cameraDAO.getAll() == null) {
            obj = new ModelAndView("settings/cameras");
            obj.addObject("contentHeader", "Detection cameras");
            obj.addObject("contentDescription", "Cameras");
            obj.addObject("addCamera", "Edit IP cameras");
        } else {
            obj = new ModelAndView("dashboard");
            obj.addObject("countCars", carDAO.getAll());
            obj.addObject("countUsers", userRepository.findAllByDeletedIsFalse());
            obj.addObject("contentHeader", "Dashboard");
            obj.addObject("contentDescription", "Main page");
        }

        return obj;
    }

    @RequestMapping(value = "/settings/global")
    public ModelAndView actionDetectionSettings() {
        ModelAndView obj = new ModelAndView("settings/settings");
        obj.addObject("contentHeader", "Detection settings");
        obj.addObject("contentDescription", "Settings for OpenALPR");
        return obj;
    }

    @RequestMapping(value = "/settings/camera")
    public ModelAndView actionCamera() {
        ModelAndView obj = new ModelAndView("settings/cameras");
        obj.addObject("contentHeader", "Cameras");
        obj.addObject("contentDescription", "Edit IP cameras");
        return obj;
    }

    @RequestMapping(value = "/settings/profile")
    public ModelAndView actionProfileSettings() {
        ModelAndView obj = new ModelAndView("settings/profile");
        obj.addObject("contentHeader", "Profile");
        obj.addObject("contentDescription", "Edit your profile data");
        return obj;
    }

    @RequestMapping(value = "/users/index")
    public ModelAndView actionUsers() {
        ModelAndView obj = new ModelAndView("users/index");
        obj.addObject("contentHeader", "User list");
        obj.addObject("contentDescription", "All registered users in the system");
        return obj;
    }

    @RequestMapping(value = "/users/create")
    public ModelAndView actionUsersAdd() {
        ModelAndView obj = new ModelAndView("users/create");
        obj.addObject("contentHeader", "New user");
        obj.addObject("contentDescription", "Add new user to the system");
        return obj;
    }

    @RequestMapping(value = "/cars/index")
    public ModelAndView actionCars() {
        ModelAndView obj = new ModelAndView("cars/index");
        obj.addObject("contentHeader", "Cars");
        obj.addObject("contentDescription", "List of detected cars");
        return obj;
    }

    @GetMapping(value = "/for-drivers")
    public String actionPageForDrivers() {
        return "drivers";
    }

    @GetMapping(value = "/show-departure")
    public ResponseEntity<Car> getSelectedCamera() {
        Car car = carDAO.getLastDeparture();
        LocalDateTime arrival = LocalDateTime.parse(car.getDateArrival());
        LocalDateTime departure = LocalDateTime.parse(car.getDateDeparture());

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        car.setDateArrival(arrival.format(format));
        car.setDateDeparture(departure.format(format));

        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping(value = "/alpr-json")
    public ResponseEntity<String> actionDetector(@RequestBody String results) {
        logger.info("### detectFromString() ###");
        logger.info(results);
        if (results != null) {
            JsonObject jsonObject = new JsonParser().parse(results).getAsJsonObject(); //.getAsJsonObject("best_plate");
            // According to data_type jsonObject will be sent to different methods
            if (jsonObject.get("data_type").toString().equals("\"heartbeat\"")) {
                handler.saveCameras(jsonObject);
            } else if (jsonObject.get("data_type").toString().equals("\"alpr_group\"")) {
                handler.saveDetection(jsonObject);
            }
        } else {
            logger.info("Listening...");
        }

        return new ResponseEntity<>("Information acceptet", HttpStatus.OK);
    }

    @RequestMapping("status/404")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "/status/404";
    }
}
