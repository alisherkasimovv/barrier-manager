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
 * Car DAO.
 *
 * @author Alisher Kasimov
 * @version 0.1.0046
 */
@Entity
@Table(name = "cameras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Camera {

    @Id
    @Column(name = "c_id", columnDefinition = "serial")
    private int id;

    @Column(name = "c_name")
    private String name;

    @Column(name = "c_url")
    private String url;
}
