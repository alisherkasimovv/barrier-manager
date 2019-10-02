package uz.barriermanager.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

/**
 * Reserved car class.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
@Entity
@Table(name = "cars_reserved")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserved {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cr_id", columnDefinition = "serial")
    private int id;

    @Nullable
    @Column(name = "plate_number")
    private String plateNumber;

    @Nullable
    @Column(name = "description")
    private String description;

    @Nullable
    @Column(name = "plate_image", columnDefinition = "TEXT")
    private String plateImage;
}
