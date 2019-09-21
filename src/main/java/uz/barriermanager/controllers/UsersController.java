package uz.barriermanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.barriermanager.models.User;
import uz.barriermanager.services.dao.interfaces.UserDAO;

import javax.validation.Valid;
import java.util.List;

/**
 * Users controller class.
 *
 * @author Alisher Kasimov
 * @version 0.1.0047
 */
@Controller
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<User>> getFullUserList() {
        return new ResponseEntity<>(userDAO.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<List<User>> deleteSelectedUser(@Valid @RequestBody int id) {
        userDAO.deleteUser(id);
        return new ResponseEntity<>(userDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView actionEditUser() {
        ModelAndView obj = new ModelAndView("users/create");
        obj.addObject("contentHeader", "Create");
        obj.addObject("contentDescription", "Create new user");
        return obj;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(User user) {
        userDAO.saveUser(user);
        return "redirect:/users/index";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView actionEditUser(@PathVariable("id") int id) {
        ModelAndView obj = new ModelAndView("users/create");
        obj.addObject("user", userDAO.getOneUser(id));
        obj.addObject("contentHeader", "Edit user");
        obj.addObject("contentDescription", "Edit credentials for user.");
        return obj;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(User user) {
        userDAO.editUser(user);
        return "redirect:/users/index";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") int id) {
        userDAO.deleteUser(id);
        return "redirect:/users/index";
    }
}
