package uz.barriermanager.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Car class.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
@Entity
@Table(name = "cars_detected")
@Component("car-instance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cd_id", columnDefinition = "serial")
    private int id;

    @Nullable
    @Column(name = "plate_number")
    private String plateNumber;

    @Nullable
    @Column(name = "recognizing_time_arrival")
    private double recognizingTimeArrival;

    @Nullable
    @Column(name = "recognizing_time_departure")
    private double recognizingTimeDeparture;

    @Nullable
    @Column(name = "confidence_arrival")
    private double confidenceArrival;

    @Nullable
    @Column(name = "confidence_departure")
    private double confidenceDeparture;

    @Nullable
    @Column(name = "picture_arrival", columnDefinition = "TEXT")
    private String pictureArrival;

    @Nullable
    @Column(name = "picture_arrival_full", columnDefinition = "TEXT")
    private String pictureArrivalFull;

    @Nullable
    @Column(name = "picture_departure", columnDefinition = "TEXT")
    private String pictureDeparture;

    @Nullable
    @Column(name = "picture_departure_full", columnDefinition = "TEXT")
    private String pictureDepartureFull;

    @Nullable
    @Column(name = "is_gone")
    private boolean gone = false;

    @Nullable
    @Column(name = "time_arrival")
    private String dateArrival;

    @Nullable
    @Column(name = "time_departure")
    private String dateDeparture;

    @Nullable
    @Column(name = "time_spent")
    private long timeSpent;

    @Nullable
    @Column(name = "cost")
    private long cost;

    @Nullable
    @Column(name = "last_departure")
    private boolean lastDeparture;
}


