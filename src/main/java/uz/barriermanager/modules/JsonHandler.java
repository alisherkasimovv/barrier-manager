package uz.barriermanager.modules;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.barriermanager.SpringContext;
import uz.barriermanager.models.Camera;
import uz.barriermanager.models.Car;
import uz.barriermanager.services.dao.interfaces.CameraDAO;
import uz.barriermanager.services.dao.interfaces.CarDAO;
import uz.barriermanager.services.dao.interfaces.SettingsDAO;

@Component
public class JsonHandler {
    @Autowired
    CameraDAO cameraDAO;

    @Autowired
    CarDAO carDAO;

    private Logger logger = LoggerFactory.getLogger(JsonHandler.class);

    public JsonHandler() {
        cameraDAO = SpringContext.getBean(CameraDAO.class);
        carDAO = SpringContext.getBean(CarDAO.class);
    }

    public void saveDetection(JsonObject jsonObject) {
        Camera cam = cameraDAO.getCamera(jsonObject.get("camera_id").toString().replace("\"", ""));
        logger.info("Selected camera with id: " + cam.getCameraId());
        String fullImage = jsonObject.get("vehicle_crop_jpeg").toString().replace("\"", "");

        JsonObject obj = jsonObject.getAsJsonObject("best_plate");

        if (cam.isCameraForArrivals()) {
            Car car = new Car();
            car.setPlateNumber(obj.getAsJsonObject().get("plate").toString().replace("\"", ""));
            car.setConfidenceArrival(obj.getAsJsonObject().get("confidence").getAsDouble());
            car.setPictureArrival(obj.getAsJsonObject().get("plate_crop_jpeg").toString().replace("\"", ""));
            car.setPictureArrivalFull(fullImage);

            carDAO.saveArrival(car);
        } else {
            Car temp = carDAO.getCar(obj.getAsJsonObject().get("plate").toString().replace("\"", ""));
            if (temp != null) {
                temp.setConfidenceDeparture(obj.getAsJsonObject().get("confidence").getAsDouble());
                temp.setPictureDeparture(obj.getAsJsonObject().get("plate_crop_jpeg").toString().replace("\"", ""));
                temp.setPictureDepartureFull(fullImage);

                carDAO.saveDeparture(temp);
            }

            logger.info("Car won't be saved as departured, there is no arrival for that car.");
        }
    }

    public void saveCameras(JsonObject jsonObject) {
        if (jsonObject != null) {
            JsonArray streamJsonArray = jsonObject.getAsJsonArray("video_streams");
            for (int i = 0; i < streamJsonArray.size(); i++) {
                JsonObject obj = streamJsonArray.get(i).getAsJsonObject();

                Camera cam = new Camera();
                cam.setName(obj.get("camera_name").toString().replace("\"", ""));
                cam.setCameraId(obj.get("camera_id").toString().replace("\"", ""));
                cam.setUrl(obj.get("url").toString().replace("\"", ""));
                cam.setStreaming(obj.get("is_streaming").getAsBoolean());

                if (!cameraDAO.isExist(cam.getCameraId())) {
                    cameraDAO.createCamera(cam);
                } else {
                    return;
                }
            }
        }
    }
}
