package com.studentmgt.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class User extends Person {
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String userName;
    @ManyToOne
    private Location village;

    // Getters and setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Location getVillage() {
        return village;
    }

    public void setVillage(Location village) {
        this.village = village;
    }
}
