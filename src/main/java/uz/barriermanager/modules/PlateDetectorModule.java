package uz.barriermanager.modules;

import com.openalpr.jni.Alpr;
import com.openalpr.jni.AlprException;
import com.openalpr.jni.AlprPlateResult;
import com.openalpr.jni.AlprResults;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * PlateDetectorModule class.
 * While PlateDetectorModule class constructed
 *
 * @author Alisher Kasimov
 * @version 0.1.0046
 */
public class PlateDetectorModule {
    private Alpr alpr;

    private String detectedPlate;
    private float confidence;
    private float processingTime;

    /**
     * PlateDetectorModule constructor.
     * @param country get string of country code.
     * @param configs path to .conf file.
     * @param runtime path to runtime_data directory.
     * @param detectCount number of detections.
     */
    public PlateDetectorModule(String country, String configs, String runtime, int detectCount) {
        alpr = new Alpr(country, configs, runtime);
        alpr.setTopN(detectCount);
        alpr.setDefaultRegion("wa");
    }

    public void startDetecting(String plateImage) {
        try {
            Path path = Paths.get(plateImage);
            byte[] imageData = Files.readAllBytes(path);
            AlprResults results = alpr.recognize(imageData);

            processingTime = results.getTotalProcessingTimeMs();

            for (AlprPlateResult result : results.getPlates()) {
                detectedPlate = result.getBestPlate().getCharacters();
                confidence = result.getBestPlate().getOverallConfidence();
            }

        } catch (IOException e) {
            System.out.println("There was an error while reading bytes from path\n");
            e.printStackTrace();
        } catch (AlprException e) {
            System.out.println("There was an error while recognizing plateImage\n");
            e.printStackTrace();
        }
    }

    /**
     * Calling stopAlpr method unloads ALPR.
     * Method should be called after detection is performed fully.
     */
    public void stopAlpr() {
        alpr.unload();
    }

    public String getDetectedPlate() {
        return detectedPlate;
    }

    public float getConfidence() {
        return confidence;
    }

    public float getProcessingTime() {
        return processingTime;
    }
}
