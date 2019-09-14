package uz.barriermanager.modules;

import org.apache.commons.lang3.RandomStringUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.springframework.beans.factory.annotation.Autowired;
import uz.barriermanager.models.Car;
import uz.barriermanager.models.Settings;
import uz.barriermanager.services.dao.interfaces.CarDAO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * VideoStreamerModule class.
 * Class works for capturing from stream and returning ImageBuffer.
 *
 * @author Alisher Kasimov
 * @version 0.1.0046
 */
public class VideoStreamerModule {
    @Autowired
    CarDAO carDAO;

    private Mat mat;
    private BufferedImage bufferedImage;
    private VideoCapture capture;

    private String url;
    private ArrayList<String> frames;
    private PlateDetectorModule detectorModule;

    private boolean continueStream;
    private boolean isCapturing = true;
    private int captureCount;
    private int shotInterval;

    /*
     * TODO: captureCount and shotInterval should be retrieved from DB.
     * TODO: Maybe stream url could be retrieved from DB, because camera's IP in some sense will be same.
     */

    public VideoStreamerModule() {
    }

    public VideoStreamerModule(String url, Settings settings) {
        this.url = url;

//        this.detectorModule = new PlateDetectorModule();

        this.captureCount = 5;
        this.shotInterval = 10000;

        continueStream = true;
    }

    /**
     * Method starts streaming process.
     * For this it checks url, then opens new thread to start video streaming.
     */
    public void startStreaming() {
        if (url != null) {
            continueStream = true;
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

            capture = new VideoCapture(url);
            mat = new Mat();

            // Creating new thread and starting capture.
            new VidCapture().start();
        } else {
            System.out.println("There was an error, while trying to start streaming");
        }
    }

    public void startDetecting() {
        new SaveFrame().start();

        if (!isCapturing) {
            new DetectFrame().start();
            DetectFrame.currentThread().interrupt();
            isCapturing = true;
        }

        SaveFrame.currentThread().interrupt();
    }

    /**
     * Thread for saving images to the storage while streaming is performed.
     */
    class SaveFrame extends Thread {
        @Override
        public void run() {
            frames = new ArrayList<>();

            for (int i = 0; i < captureCount; i++) {
                String filename = RandomStringUtils.randomAlphanumeric(15);
                filename = filename + ".jpg";
                Imgcodecs.imwrite(filename, mat);

                frames.add(filename);

                try {
                    sleep(shotInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            isCapturing = false;
        }
    }

    /**
     * Separate stream for number recognition.
     */
    class DetectFrame extends Thread {
        @Override
        public void run() {
            for (String frame : frames) {
                detectorModule.startDetecting(frame);

                Car car = new Car();
                car.setPlate(detectorModule.getDetectedPlate());
                car.setConfidence(detectorModule.getConfidence());
                car.setRecognizingTime(detectorModule.getProcessingTime());
                car.setPicture(frame);
                carDAO.saveCar(car);
            }

            detectorModule.stopAlpr();
        }
    }

    /**
     * Thread of stream capturing method.
     * During streaming method sends frames to BufferedImage with 1ms interval.
     */
    class VidCapture extends Thread {
        @Override
        public void run() {
            while (continueStream) {
                if (capture.read(mat)) {
                    bufferedImage = Mat2bufferedImage(mat);
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Converting Mat to BufferedImage.
     * @param mat Receive a frame from a VideoCapture in Mat.
     * @return Frame converted into buffered image.
     */
    private static BufferedImage Mat2bufferedImage(Mat mat) {
        MatOfByte bytemat = new MatOfByte();
        Imgcodecs.imencode(".jpg", mat, bytemat);
        byte[] bytes = bytemat.toArray();
        InputStream in = new ByteArrayInputStream(bytes);
        BufferedImage img = null;
        try {
            img = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public void setContinueStream(boolean continueStream) {
        this.continueStream = continueStream;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

// TODO: byteBuffer.
    /*
    public static ByteBuffer convertImageData(BufferedImage bi) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
        ImageIO.write(bi, "png", out);
        return ByteBuffer.wrap(out.toByteArray());
    } catch (IOException ex) {
        //
    }
    return null;
}

     */

    //    @RequestMapping(value = "/api/get-streaming", method = RequestMethod.GET)
//    public String getStreaming() {
//        service = new DetectingService("C:\\Downloads\\Video\\vid.mp4");
//        byte[] imageBytes = {};
//
//        BufferedImage bufferedImage = service.getStreaming();
//
//        try {
//            ImageIO.write(bufferedImage,"png",new File("tmpImage.png"));
//            imageBytes = Files.readAllBytes(Paths.get("tmpImage.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Base64.Encoder encoder = Base64.getEncoder();
//
//        return encoder.encodeToString(imageBytes);
//    }
}
