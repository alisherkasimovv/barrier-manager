package uz.barriermanager.services.dao.interfaces;

import uz.barriermanager.models.Camera;

import java.util.ArrayList;

/**
 * Camera DAO.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
public interface CameraDAO {
    ArrayList<Camera> getAll();
    Camera getOne(int id);
    boolean isExist(String cameraId);
    Camera getCamera(String id);
    void createCamera(Camera camera);
    void editCamera(Camera camera);
    void delete(int id);
}
