/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.db.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Tranquility
 */
@Entity
@Table(schema = "public", name = "doctor")
public class Doctor implements Serializable {

    @Id
    private UUID id;
    private String mediId;
    private String name;
    private String surname;

    public Doctor() {
    }
    
    public Doctor(String mediId, String name, String surname) {
        this.mediId = mediId;
        this.name = name;
        this.surname = surname;
    }

    public String getMediId() {
        return mediId;
    }

    public void setMediId(String mediId) {
        this.mediId = mediId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        if (id == null){
            return null;
        }
        return id.toString();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
