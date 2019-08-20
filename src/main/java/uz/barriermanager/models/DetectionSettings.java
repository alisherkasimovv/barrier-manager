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
 * @version 0.1.0012
 */
@Entity
@Table(name = "detection_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetectionSettings {
    @Id
    @Column(name = "ds_id", columnDefinition = "serial")
    private int id;

    @Column(name = "ds_country", length = 10)
    private String country;

    @Column(name = "ds_configs", length = 512)
    private String configs;

    @Column(name = "ds_runtime", length = 512)
    private String runtime;
}
