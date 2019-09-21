package uz.barriermanager.services.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import uz.barriermanager.models.Camera;
import uz.barriermanager.repositories.CameraRepository;
import uz.barriermanager.services.dao.interfaces.CameraDAO;

import java.util.ArrayList;

/**
 * Implementation of CameraDAO interface.
 *
 * @author Alisher Kasimov
 * @version 0.1.0047
 */
public class CameraDAOImpl implements CameraDAO {
    @Autowired
    @Qualifier("CameraRepository")
    private CameraRepository repository;

    @Override
    public ArrayList<Camera> getAll() {
        return repository.findAll();
    }

    @Override
    public Camera getOne(int id) {
        return repository.getById(id);
    }

    @Override
    public void createCamera(Camera camera) {
        repository.save(camera);
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
