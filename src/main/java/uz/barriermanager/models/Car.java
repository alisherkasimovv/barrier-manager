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
 * @version 0.1.0047
 */
@Entity
@Table(name = "detected_cars")
@Component("car-instance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dc_id", columnDefinition = "serial")
    private int id;

    @Nullable
    @Column(name = "dc_plate")
    private String plate;

    @Nullable
    @Column(name = "dc_recognizing_time")
    private double recognizingTime;

    @Nullable
    @Column(name = "dc_confidence")
    private double confidence;

    @Nullable
    @Column(name = "dc_picture")
    private String picture;

    @Nullable
    @Column(name = "dc_departured")
    private boolean departured = false;

    @Nullable
    @Column(name = "dc_arrival")
    private String dateArrival;

    @Nullable
    @Column(name = "dc_departure")
    private String dateDeparture;

    @Nullable
    @Column(name = "dc_time_spent")
    private long timeSpent;

    @Nullable
    @Column(name = "dc_is_deleted")
    private boolean deleted;
}


