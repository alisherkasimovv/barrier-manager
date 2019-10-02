package uz.barriermanager.modules;

import org.apache.commons.lang3.RandomStringUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * @version 0.1.0055
 */
public class VideoStreamerModule {

    private Mat mat;
    private BufferedImage bufferedImage;
    private VideoCapture capture;

    private String url;

    private ArrayList<String> frames;

    private boolean continueStream;
    private boolean isCapturing = true;
    private int captureCount;
    private int shotInterval;

    public VideoStreamerModule(String url, Settings settings) {
        this.url = url;

        this.captureCount = settings.getStreamShotCount();
        this.shotInterval = settings.getStreamShotIntervalMs();

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

    /**
     * Method starts new SaveFrame thread for capturing.
     * Sleeps 2000ms before interrupting SaveFrame thread instance.
     */
    public void startDetecting() {
        new SaveFrame().start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

                if  (mat.size() != null)
                    Imgcodecs.imwrite(filename, mat);
                else
                    continue;

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

    public ArrayList<String> getFrames() {
        return frames;
    }
}
