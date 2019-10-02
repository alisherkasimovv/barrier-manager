package uz.barriermanager.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Settings object for OpenALPR
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
@Entity
@Table(name = "main_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Settings {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "s_id", columnDefinition = "serial")
    private int id;

    @Column(name = "country", length = 10)
    private String country;

    @Column(name = "configs", length = 512)
    private String configs;

    @Column(name = "runtime", length = 512)
    private String runtime;

    @Column(name = "shot_count")
    private int streamShotCount;

    @Column(name = "shot_interval")
    private int streamShotIntervalMs;

    @Column(name = "price_per_hour")
    private double price;
}
