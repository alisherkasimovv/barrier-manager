package uz.barriermanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import uz.barriermanager.repositories.CarRepository;
import uz.barriermanager.repositories.UserRepository;

/**
 * Main controller class.
 *
 * @author Alisher Kasimov
 * @version 0.1.0035
 */
@Controller
public class MainController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/", "/index"}, name = "index")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("dashboard");
        model.addObject("countCars", carRepository.findAllByDeletedIsFalse());
        model.addObject("countUsers", userRepository.findAllByDeletedIsFalse());
        model.addObject("contentHeader", "Dashboard");
        model.addObject("contentDescription", "Main page");
        return model;
    }

    @RequestMapping(value = "/settings/detection")
    public ModelAndView actionDetectionSettings() {
        ModelAndView obj = new ModelAndView("settings/detection");
        obj.addObject("contentHeader", "Detection settings");
        obj.addObject("contentDescription", "Settings for OpenALPR");
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

    @RequestMapping("status/404")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "/status/404";
    }
}