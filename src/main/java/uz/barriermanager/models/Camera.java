package uz.barriermanager.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

/**
 * Car DAO.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
@Entity
@Table(name = "cameras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Camera {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "c_id", columnDefinition = "serial")
    private int id;

    @Nullable
    @Column(name = "camera_id", unique = true)
    private String cameraId;

    @Nullable
    @Column(name = "camera_name")
    private String name;

    @Nullable
    @Column(name = "camera_url")
    private String url;

    @Nullable
    @Column(name = "is_streaming")
    private boolean isStreaming;

    @Nullable
    @Column(name = "camera_for_arrivals")
    private boolean cameraForArrivals;
}
