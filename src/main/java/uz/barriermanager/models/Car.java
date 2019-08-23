package uz.barriermanager.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Car class.
 *
 * @author Alisher Kasimov
 * @version 0.1.0035
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dc_id", columnDefinition = "serial")
    private int id;

    @Column(name = "dc_plate")
    private String plate;

    @Column(name = "dc_recognizing_time")
    private double recognizingTime;

    @Column(name = "dc_confidence")
    private double confidence;

    @Column(name = "dc_picture")
    private String picture;

    @Column(name = "dc_deleted")
    private boolean deleted = false;
}


