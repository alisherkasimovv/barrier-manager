package uz.barriermanager.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.barriermanager.models.Camera;
import uz.barriermanager.services.dao.CameraDAOImpl;
import uz.barriermanager.services.dao.interfaces.CameraDAO;

import javax.validation.Valid;
import java.util.List;

/**
 * Camera controller class.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
@Controller
@RequestMapping("/camera")
public class CameraController {
    @Autowired
    private CameraDAO cameraDAO;

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<Camera>> openCameraData() {
        return new ResponseEntity<>(cameraDAO.getAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<List<Camera>> saveCamera(@Valid @RequestBody Camera camera) {
        cameraDAO.createCamera(camera);
        return new ResponseEntity<>(cameraDAO.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Camera> getSelectedCamera(@PathVariable int id) {
        return new ResponseEntity<>(cameraDAO.getOne(id), HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<List<Camera>> updateCamera(@Valid @RequestBody Camera camera) {
        cameraDAO.editCamera(camera);
        return new ResponseEntity<>(cameraDAO.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<List<Camera>> deleteCamera(@Valid @RequestBody int id) {
        cameraDAO.delete(id);
        return new ResponseEntity<>(cameraDAO.getAll(), HttpStatus.OK);
    }
}
