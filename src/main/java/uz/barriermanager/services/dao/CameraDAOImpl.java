package uz.barriermanager.services.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import uz.barriermanager.models.Camera;
import uz.barriermanager.repositories.CameraRepository;
import uz.barriermanager.services.dao.interfaces.CameraDAO;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

/**
 * Implementation of CameraDAO interface.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
public class CameraDAOImpl implements CameraDAO {
    @Autowired
    @Qualifier("CameraRepository")
    private CameraRepository repository;

    @Override
    public ArrayList<Camera> getAll() {
        if (repository.findAll() == null)
            return null;
        return repository.findAll();
    }

    @Override
    public Camera getOne(int id) {
        if (repository.getById(id) == null)
            return null;
        return repository.getById(id);
    }

    @Override
    public boolean isExist(String cameraId) {
        return repository.existsByCameraId(cameraId);
    }

    @Override
    public Camera getCamera(String id) {
        return repository.getByCameraId(id);
    }

    @Override
    public void createCamera(Camera camera) {
        try {
            repository.save(camera);
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations();
        }
    }

    @Override
    public void editCamera(Camera camera) {
        Camera temp = repository.getById(camera.getId());

        temp.setName(camera.getName());
        temp.setUrl(camera.getUrl());

        repository.save(temp);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
