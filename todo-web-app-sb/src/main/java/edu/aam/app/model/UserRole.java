package edu.aam.app.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Arif A Mondal
 * @since 30-11-2019
 */
@Entity
@Table(name = "ROLES")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String roleName;

    public Long getRno() {
        return rno;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
