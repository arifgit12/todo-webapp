package edu.aam.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Arif A Mondal
 * @since 30-11-2019
 */
/*
users and roles concept are taken from the following link
https://www.baeldung.com/role-and-privilege-for-spring-security-registration
 */
@Entity
@Table(name = "ROLES")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
