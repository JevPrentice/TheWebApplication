/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.db.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
@JsonTypeName("Doctor")
public class DoctorImpl implements Doctor {

    @Id
    private UUID id;
    private String mediId;
    private String name;
    private String surname;

    public DoctorImpl() {
    }

    @JsonCreator
    public DoctorImpl(@JsonProperty("medID") String mediId, @JsonProperty("name") String name, @JsonProperty("surname") String surname) {
        this.mediId = mediId;
        this.name = name;
        this.surname = surname;
    }

//    @JsonProperty("medID")
    @Override
    public String getMediId() {
        return mediId;
    }

//    @JsonProperty("name")
    @Override
    public String getName() {
        return name;
    }

//    @JsonProperty("surname")
    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        if (id == null) {
            return null;
        }
        return id.toString();
    }

//    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

//    public void setId(UUID id) {
//        this.id = id;
//    }
//     public void setMediId(String mediId) {
//        this.mediId = mediId;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//    
//     public void setSurname(String surname) {
//        this.surname = surname;
//    }
}
