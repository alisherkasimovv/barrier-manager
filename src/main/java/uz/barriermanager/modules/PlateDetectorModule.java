package uz.barriermanager.modules;

import com.openalpr.jni.Alpr;
import com.openalpr.jni.AlprException;
import com.openalpr.jni.AlprPlateResult;
import com.openalpr.jni.AlprResults;
import uz.barriermanager.models.Car;
import uz.barriermanager.models.Settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * PlateDetectorModule class.
 * While PlateDetectorModule class constructed
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
public class PlateDetectorModule {
    private Alpr alpr;

    /**
     * PlateDetectorModule constructor.
     *
     * @param settings Settings for OpenALPR module.
     */
    public PlateDetectorModule(Settings settings) {
        alpr = new Alpr(settings.getCountry(), settings.getConfigs(), settings.getRuntime());
        alpr.setTopN(10);
        alpr.setDefaultRegion("eu");
    }

//    public Car startDetecting(String plateImage) {
//        Car car = new Car();
//        try {
//            Path path = Paths.get(plateImage);
//            byte[] imageData = Files.readAllBytes(path);
//            AlprResults results = alpr.recognize(imageData);
//
//            car.setRecognizingTime(results.getTotalProcessingTimeMs());
//
//            for (AlprPlateResult result : results.getPlates()) {
//                car.setPlate(result.getBestPlate().getCharacters());
//                car.setConfidence(result.getBestPlate().getOverallConfidence());
//            }
//            car.setPicture(plateImage);
//        } catch (IOException e) {
//            System.out.println("There was an error while reading bytes from path\n");
//            e.printStackTrace();
//        } catch (AlprException e) {
//            System.out.println("There was an error while recognizing plateImage\n");
//            e.printStackTrace();
//        }
//
//        return car;
//    }

    /**
     * Calling stopAlpr method unloads ALPR.
     * Method should be called after detection is performed fully.
     */
    public void stopAlpr() {
        alpr.unload();
    }
}
