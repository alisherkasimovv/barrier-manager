package uz.barriermanager.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Settings object for OpenALPR
 *
 * @author Alisher Kasimov
 * @version 0.1.0046
 */
@Entity
@Table(name = "main_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Settings {
    @Id
    @Column(name = "s_id", columnDefinition = "serial")
    private int id;

    @Column(name = "s_country", length = 10)
    private String country;

    @Column(name = "s_configs", length = 512)
    private String configs;

    @Column(name = "s_runtime", length = 512)
    private String runtime;

    @Column(name = "s_shot_count")
    private int streamShotCount;

    @Column(name = "s_shot_interval")
    private int streamShotIntervalMs;

    @Column(name = "s_price_per_hour")
    private double price;
}
