package uz.barriermanager.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * User class.
 *
 * @author Alisher Kasimov
 * @version 0.1.0046
 */
@Entity
@Table(name = "user_list")
@Component("user-instance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ul_id", columnDefinition = "serial")
    private int id;

    @Column(name = "ul_username")
    private String username;

    @Column(name = "ul_password")
    private String password;

    @Column(name = "ul_role")
    private String role;

    @Column(name = "ul_registered")
    private Timestamp registered;

    @Column(name = "ul_deleted")
    private boolean deleted;
}
